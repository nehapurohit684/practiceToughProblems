package string;

public class AllienDicOrder {
    /**
     * \
     * 953. Verifying an Alien Dictionary
     * Hint: Keep each 26 char in map(array) to put an order.
     * First letter that doesnt match between two string decide if they are lexicographically sorted or not
     * do if first diff letter has some in word 1 which is  greater than corresponding order of letter at word 2 you return false
     * To check edge case, make sure string 1 length is smaller than length of word2
     *
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSorted(String[] words, String order) {
        int[] array = new int[26];

        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            array[c - 'a'] = i;
        }

        for (int i = 0; i < words.length - 1; i++) {
            if (!compareWords(words[i], words[i + 1], array)) {
                return false;
            }
        }
        return true;
    }

    private boolean compareWords(String word1, String word2, int[] array) {

        int i = 0;
        int j = 0;

        while (i < word1.length() && j < word2.length()) {

            int first = array[word1.charAt(i) - 'a'];
            int second = array[word2.charAt(j) - 'a'];
            i++;
            j++;
            if (first > second) return false;
            if (first < second) return true;

        }
        return i >= word1.length() && j <= word2.length();
    }
}
