import java.util.Arrays;
import java.util.Locale;

public class VigenereCipher {

    private String standardalphabet;
    private String key;
    private int[] space;

    @Override
    public String toString() {
        return "VigenereCipher{" +
                "standardalphabet='" + standardalphabet + '\'' +
                ", key='" + key + '\'' +
                ", space=" + Arrays.toString(space) +
                '}';
    }

    public String getStandardalphabet() {
        return standardalphabet;
    }

    public void setStandardalphabet(String standardalphabet) {
        this.standardalphabet = standardalphabet;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int[] getSpace() {
        return space;
    }

    public void setSpace(int[] space) {
        this.space = space;
    }

    public VigenereCipher(String x)
    {
        standardalphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        key = x.toLowerCase(Locale.ROOT);
        space = new int[x.length()];
        for(int i =0; i<x.length(); i++)
        {for(int k=0; k<standardalphabet.length(); k++)
            {if(x.charAt(i) == standardalphabet.charAt(k))
                {
                    space[i] = k;
                }
            }
        }

    }

    public String encode(String x)
    {
        String encode = "";
        int number;
        char character;
        int property = 0;
        for (int i = 0; i < x.length(); i++)
        {for (int k = 0; k < standardalphabet.length(); k++)
            {String notaligned = String.valueOf(x.charAt(i));
                if (!standardalphabet.contains(notaligned))
                {
                    encode = encode + notaligned;
                    property = property +1;
                    break;
                }
                else
                {
                    if (x.charAt(i) == standardalphabet.charAt(k))
                    { if (!(property >= space.length))
                        {
                            number = k + space[property];
                            character = standardalphabet.charAt(number);
                            encode = encode + character;
                            property = property +1;
                        }
                        else
                        {
                           property = 0;
                            number = k + space[property];
                            character = standardalphabet.charAt(number);
                            encode = encode + character;
                            property = property + 1;
                        }
                    }
                }
            }
        }
        return encode.toLowerCase(Locale.ROOT);
    }

    public String decode(String y)
    {
        String decode = "";
        int number;
        char character;
        int property = 0;
        for (int i = 0; i < y.length(); i++)
        {for (int k = 0; k < standardalphabet.length(); k++)
            {
                String nonAlphabet = String.valueOf(y.charAt(i));
                if (!standardalphabet.contains(nonAlphabet))
                {
                    decode = decode + nonAlphabet;
                    property = property +1 ;
                    break;
                }
                else
                {
                    if (y.charAt(i) == standardalphabet.charAt(k))
                    {
                        if (!(property >= space.length))
                        {
                            if (k - space[property] <= 0)
                            {
                                number = k + space[property];
                                System.out.println("." + number);
                            }
                            else
                            {
                                number = k - space[property];
                                System.out.println("." + number);
                            }

                            character = standardalphabet.charAt(number);
                            System.out.println("!" + character);
                            decode = decode + character;
                            System.out.println(decode);
                            property = property +1;
                        }
                        else
                        { property = 0;
                            number = k - space[property];
                            if (number < 0)
                            {
                                number = Math.abs(number);
                            }

                            character = standardalphabet.charAt(number);
                            decode = decode + character;
                            property = property+1;
                        }
                    }
                }
            }
        }
        return decode.toLowerCase(Locale.ROOT);
    }
}
