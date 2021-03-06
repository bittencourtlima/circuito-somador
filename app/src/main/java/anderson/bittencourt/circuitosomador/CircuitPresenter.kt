package anderson.bittencourt.circuitosomador

import android.util.Log
import java.lang.StringBuilder
import kotlin.math.pow

class CircuitPresenter: ContractCircuit.Presenter {

    companion object {
        val BASE_BINARIA: Int = 2
    }

    override fun sumBits(input1: String, input2: String): String {
        var values1 = convertToList(input1)
        var values2 = convertToList(input2)

        values1 = prepareList(values1, values2.size)
        values2 = prepareList(values2, values1.size)
        return sumBits(values1, values2)
    }

    private fun prepareList(values: MutableList<Int>, size: Int): MutableList<Int> {
        while(values.size < size){
            values.add(0, 0)
        }
        return values;
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
            val carryIn = listCarryOut[index + 1]

            var sumWithoutCarryIn = sumBits(value1, value2)
            val finalSum = sumBits(sumWithoutCarryIn, carryIn)
            listResult.add(0,finalSum)

            var carryOut = calculateCarryOut(value1, value2, sumWithoutCarryIn, carryIn)
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

    private fun convertToList(number: String): MutableList<Int> {
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

    private fun OR(bit1: Boolean, bit2: Boolean): Int{
        val result = bit1 || bit2
        return if(result) 1 else 0
    }

    private fun sumBits(bit1: Int, bit2: Int): Int{
        val boolean1 = bit1 == 1
        val boolean2 = bit2 == 1
        return XOR(boolean1, boolean2)
    }

    private fun calculateCarryOut(bit1: Int, bit2: Int, sum: Int, carryIn: Int): Int{
        val booleanBit1 = bit1 == 1
        val booleanBit2 = bit2 == 1
        val booleanSum = sum == 1
        val booleanCarryIn = carryIn == 1
        return OR(AND(booleanBit1, booleanBit2) == 1, AND( booleanCarryIn, booleanSum) == 1)
    }
}