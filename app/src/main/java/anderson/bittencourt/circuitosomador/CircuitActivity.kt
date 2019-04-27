package anderson.bittencourt.circuitosomador

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_circuit.*

class CircuitActivity : AppCompatActivity() {

    val presenter = CircuitPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circuit)
        setupOnClick()
    }

    private fun setupOnClick() {
        inputBit1.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus) convertToDecimal(v as EditText, outputBit1)
        }

        inputBit2.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus) convertToDecimal(v as EditText, outputBit2)
        }

        convert.setOnClickListener{
            convert()
        }
    }

    private fun convert() {
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
}
