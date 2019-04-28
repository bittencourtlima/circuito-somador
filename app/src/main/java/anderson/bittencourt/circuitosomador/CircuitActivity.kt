package anderson.bittencourt.circuitosomador

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_circuit.*
import kotlinx.android.synthetic.main.activity_circuit.view.*
import android.app.Activity
import android.support.v4.content.ContextCompat.getSystemService
import android.view.View
import android.view.inputmethod.InputMethodManager


class CircuitActivity : AppCompatActivity() {

    val presenter = CircuitPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circuit)
        setupOnClick()
    }

    private fun setupOnClick() {

        inputBit1.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                convertToDecimal(inputBit1, outputBit1)
            }
        })


        inputBit2.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                convertToDecimal(inputBit2, outputBit2)
            }
        } )

        convert.setOnClickListener{
            convert()
        }
    }

    private fun convert() {
        closeKeyboard()
        val binaryOutput = presenter.sumBits(inputBit1.text.toString(), inputBit2.text.toString())
        outputSomaBinary.text = binaryOutput
        outputSomaDecimal.text = presenter.convertBinaryToDecimal(binaryOutput)
    }

    private fun convertToDecimal(input: EditText, output: TextView){
        val binary = input.text.toString()
        if(!binary.isEmpty()){
            output.text = presenter.convertBinaryToDecimal(binary)
        }
    }

    private fun closeKeyboard(){
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }
}
