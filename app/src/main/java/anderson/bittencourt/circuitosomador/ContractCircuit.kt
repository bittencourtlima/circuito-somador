package anderson.bittencourt.circuitosomador

interface ContractCircuit {

    interface Presenter {
        fun sumBits(input1: String, input2: String): String
        fun convertBinaryToDecimal(binary: String): String
    }

    interface View{

    }
}