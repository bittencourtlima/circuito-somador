package anderson.bittencourt.circuitosomador

import java.lang.StringBuilder
import kotlin.math.pow

class CircuitPresenter: ContractCircuit.Presenter {

    companion object {
        val BASE_BINARIA: Int = 2
    }

    override fun sumBits(input1: String, input2: String): String {
        val values1 = convertToList(input1)
        val values2 = convertToList(input2)

        return sumBits(values1, values2)
    }

    private fun sumBits(values1: List<Int>, values2: List<Int>): String {
        val listCarryOut = ArrayList<Int>()
        for (i in values1){
            listCarryOut.add(0)
        }
        listCarryOut.add(0)

        val listResult = ArrayList<Int>()
        for(index in values1.size - 1 downTo 0 step 1){
            val value1: Int = values1[index]
            val value2: Int = values2[index]

            var sum = sumBits(value1, value2)
            sum = sumBits(sum, listCarryOut[index + 1])
            listResult.add(0,sum)

            var carryOut = calculateCarryOut(value1, value2)
            //carryOut = calculateCarryOut(carryOut, sum)

            listCarryOut[index] = carryOut
        }

        val builder = StringBuilder()

        builder.append(listCarryOut[0].toString())
        for(bit in listResult){
            builder.append(bit.toString())
        }


        return builder.toString()
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

    private fun XOR(bit1: Boolean, bit2: Boolean): Int{
        val result = (!bit1 && bit2) || (bit1 && !bit2)
        return if(result) 1 else 0
    }

    private fun AND(bit1: Boolean, bit2: Boolean): Int{
        val result = bit1 && bit2
        return if(result) 1 else 0
    }

    private fun sumBits(bit1: Int, bit2: Int): Int{
        val boolean1 = bit1 == 1
        val boolean2 = bit2 == 1
        return XOR(boolean1, boolean2)
    }

    private fun calculateCarryOut(bit1: Int, bit2: Int): Int{
        val boolean1 = bit1 == 1
        val boolean2 = bit2 == 1
        return AND(boolean1, boolean2)
    }
}