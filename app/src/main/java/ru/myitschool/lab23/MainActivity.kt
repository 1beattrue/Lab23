package ru.myitschool.lab23

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.myitschool.lab23.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    private val captions by lazy {
        resources.getStringArray(R.array.text_view_captions)
    }

    private val editTextTags by lazy {
        resources.getStringArray(R.array.edit_text_tags)
    }

    private var lower = 0
    private var upper = 0

    private val editTexts = mutableMapOf<Int, EditText>()

    private val clipboard by lazy {
        getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lower = intent.extras?.getInt(KEY_LOWER) ?: VALUE_LOWER
        upper = intent.extras?.getInt(KEY_UPPER) ?: VALUE_UPPER

        for (index in lower until upper) {
            binding.root.addView(createLinearLayout(index))
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.metrics
            .onEach { metrics ->
                for (i in lower until upper) {
                    editTexts[i]?.let { editText ->
                        if (!editText.hasFocus()) {
                            if (metrics[i] != VALUE_START) {
                                editText.setText(metrics[i].toString())
                            } else {
                                editText.text = null
                            }
                        }
                    }
                }
            }
            .launchIn(lifecycleScope)
    }

    private fun createLinearLayout(index: Int): LinearLayout {
        val linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        linearLayout.addView(createTextView(index))
        linearLayout.addView(createEditText(index))

        return linearLayout
    }

    private fun createTextView(index: Int): TextView {
        return TextView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            text = captions[index]
            setOnClickListener {
                copyToClipboard(editTexts[index]?.text.toString())
            }
        }
    }

    private fun createEditText(index: Int): EditText {
        val editText = EditText(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            hint = getString(R.string.start_value)
            tag = editTextTags[index]
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(oldText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (hasFocus()) viewModel.recalculate(index, oldText.toString())
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })
        }

        editTexts[index] = editText

        return editText
    }

    private fun copyToClipboard(text: String) {
        val clip = ClipData.newPlainText(KEY_COPY, text)
        clipboard.setPrimaryClip(clip)
    }

    companion object {
        private const val VALUE_START = 0.0
        private const val KEY_COPY = "copy key"
        private const val KEY_LOWER = "lower"
        private const val KEY_UPPER = "upper"
        private const val VALUE_LOWER = 0
        private const val VALUE_UPPER = 25
    }
}

