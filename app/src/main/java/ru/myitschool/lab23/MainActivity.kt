package ru.myitschool.lab23

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import ru.myitschool.lab23.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    private var lower = 0
    private var upper = 0

    private lateinit var captions: Array<String>
    private lateinit var editTextTags: Array<String>

    private val editTexts = mutableMapOf<Int, EditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lower =
            if (intent.extras?.get("lower") != null) intent.extras?.get("lower") as Int else 5
        upper =
            if (intent.extras?.get("upper") != null) intent.extras?.get("upper") as Int else 20

        captions = resources.getStringArray(R.array.text_view_captions)
        editTextTags = resources.getStringArray(R.array.edit_text_tags)

        for (i in lower until upper) {
            binding.root.addView(createLinearLayout(i))
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.metrics.observe(this) {
            for (i in lower until upper) {
                if (!editTexts[i]?.hasFocus()!!) {
                    if (it[i] != 0.0) {
                        editTexts[i]?.setText(it[i].toString())
                    } else {
                        editTexts[i]?.setText("")
                    }
                }
            }
        }
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
        }
    }

    private fun createEditText(index: Int): EditText {
        val editText = EditText(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            hint = getString(R.string._0)
            tag = editTextTags[index]
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(oldText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (editText.hasFocus()) viewModel.recalculate(index, oldText.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        editTexts[index] = editText

        return editText
    }
}

