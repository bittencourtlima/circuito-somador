package anderson.bittencourt.circuitosomador

import kotlin.math.pow

class CircuitPresenter: ContractCircuit.Presenter {

    companion object {
        val BASE_HEXADECIMAL: Int = 16
        val BASE_BINARIA: Int = 2
    }

    override fun sumBits(input1: String, input2: String): String {
        return ""
    }

    override fun convertBinaryToDecimal(binary: String): String {
        val values = convertToList(binary)
        return convertToDec(values, BASE_BINARIA).toString()
    }

    private fun convertToList(number: String): List<Int> {
        val values = mutableListOf<Int>()
        for(position in number){
            when(position){
                'A' -> values.add(10)
                'B' -> values.add(11)
                'C' -> values.add(12)
                'D' -> values.add(13)
                'E' -> values.add(14)
                'F' -> values.add(15)
                else -> values.add(position.toString().toInt())
            }
        }
        return values
    }

    private fun convertToDec(values: List<Int>, base: Int ): Long{
        var result: Long = 0
        var position = values.size - 1
        for(value in values){
            result = result.plus(value.times(base.toDouble().pow(position))).toLong()
            position--;
        }
        return result
    }
}