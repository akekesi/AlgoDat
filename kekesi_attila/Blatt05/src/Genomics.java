public class Genomics {

    //TODO
    
    /**
     * @param strang the String that is being sequenced (e.g. CAGTCCAGTCAGTC)
     * @param dictionary a dictionary of words (e.g. {AGT, CA, CAG, GTC, TC, TCA, TCC})
     * @return number of possible ways to compose strang using words from the dictionary
     */
    public static long optBottomUp(String strang, String[] dictionary)
    {
        // TODO
        long tmpN;
        int lengthWord;
        int lengthStrang = strang.length();
        long[] sequences = new long[lengthStrang];
        for (int i = lengthStrang - 1; i > -1; i--) {
            tmpN = 0;
            for (String word : dictionary) {
                if (strang.startsWith(word, i)) {
                    lengthWord = word.length();
                    if (lengthWord < lengthStrang - i) {
                        tmpN += sequences[i + lengthWord];
                    } else {
                        tmpN += 1;
                    }
                }
            }
            sequences[i] = tmpN;
        }
        return sequences[0];
    }

    public static void main(String[] args)
    {
        String strang = "CAGTCCAGTCAGTC";
        String[] dictionary = {
                "AGT",
                "CA",
                "CAG",
                "GTC",
                "TC",
                "TCA",
                "TCC"
        };
        System.out.println(Genomics.optBottomUp(strang, dictionary));
    }
}
