import java.util.Set;

public class DefineLetter {
    private Integer wordLength;
    private Set<Character> characterSet;
    private Long vowelNumber;

    public DefineLetter(Integer wordLength, Set<Character> characterSet, Long vowelNumber) {
        this.wordLength = wordLength;
        this.characterSet = characterSet;
        this.vowelNumber = vowelNumber;
    }

    public Integer getWordLength() {
        return wordLength;
    }

    public void setWordLength(Integer wordLength) {
        this.wordLength = wordLength;
    }

    public Set<Character> getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(Set<Character> characterSet) {
        this.characterSet = characterSet;
    }

    public Long getVowelNumber() {
        return vowelNumber;
    }

    public void setVowelNumber(Long vowelNumber) {
        this.vowelNumber = vowelNumber;
    }

    @Override
    public String toString() {
        return "DefineLetter{" +
                "wordLength=" + wordLength +
                ", characterSet=" + characterSet +
                ", vowelNumber=" + vowelNumber +
                '}';
    }
}
