import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class DefineConstants {
    static final Pattern myPattern = Pattern.compile("\\s|\\.|;|,|$");
    static final Set<Character> vowels = new HashSet<Character>(Arrays.asList('a', 'i', 'u', 'e', 'o'));
}
