//C2049527
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Sort {

    private int swaps = 0;

    public int getSwaps() {
        return swaps;
    }

    public void setSwaps(int swaps) {
        this.swaps = swaps;
    }

    /**
     Main sort object constructor that performs required question
     */

    public void Sort() throws FileNotFoundException {
        String GPT3 = "GPT3.txt";
        String stopwords = "stopwords.txt";
        ArrayList nonStopWords = removeStopwords(GPT3,stopwords);
        for(int z = 1; z < (nonStopWords.size() % 100) - 1; z++) {
            int words = z * 100;
            ArrayList GPT3Ins = new ArrayList();
            String[] GPT3Merge = new String[words];
            for(int v = 0; v < words; v++){
                int randomNum = ThreadLocalRandom.current().nextInt(0, nonStopWords.size());
                GPT3Ins.add(nonStopWords.get(randomNum));
            }
            GPT3Merge = (String[]) GPT3Ins.toArray(GPT3Merge);
            Measure(GPT3Ins, GPT3Merge, words);
        }
    }

    /**
     Gets files and strips all punctuation and returns as a String array.
     */
    private String[] getFile(String Filename) throws FileNotFoundException {
        File myFile = new File(Filename);
        Scanner myReader = new Scanner(myFile);
        String file = "";
        String[] Fullfile;
        while (myReader.hasNextLine()) {
            file = file + " " + myReader.nextLine();
        }
        file = file.replaceAll("\\p{Punct}", "");
        file = file.replaceAll("\\d", "");
        file = file.replaceAll("”", "");
        file = file.replaceAll("“", "");
        file = file.replaceAll("’", "");
        file = file.replaceAll("–", "");
        file = file.replaceAll("—", " ");
        file = file.toLowerCase();
        Fullfile = file.split(" ");
        return Fullfile;
    }

    /**
     Removes the stop words from GPT3 and returns as an ArrayList
     */

    private ArrayList removeStopwords(String GPT3, String stopwords) throws FileNotFoundException {
        String[] GPT3Words = getFile(GPT3);
        String[] AllStopwords = getFile(stopwords);
        ArrayList nonStopWords = new ArrayList();
        //for (int z = 0; z < AllStopwords.length; z++) { System.out.println(AllStopwords[z]);}
        for (int x = 1; x < GPT3Words.length; x++) {
            for (int y = 1; y < AllStopwords.length; y++) {
                if(GPT3Words[x].equals(AllStopwords[y])){
                    GPT3Words[x] = "";
                }
            }
        }
        for (int x = 0; x < GPT3Words.length; x++) {
            if(GPT3Words[x] == ""){}
            else{
                nonStopWords.add(GPT3Words[x]);
            }
        }
        return nonStopWords;
    }

    /**
     Performs an insertion sort on Array list given and then returns it back as a String Array
     */
    public String[] insertionSort(ArrayList GPT3){
        String[] ArrGPT3 = new String[GPT3.size()];
        ArrGPT3 = (String[]) GPT3.toArray(ArrGPT3);
        for (int i = 1; i < ArrGPT3.length; i++){
            String item = ArrGPT3[i];
            int j = i - 1;
            while (j >= 0 && ArrGPT3[j].compareTo(item) > 0)   {
                    ArrGPT3[j + 1] = ArrGPT3[j];
                    setSwaps(getSwaps() + 1);
                    j = j - 1;
                }
            ArrGPT3[j + 1] = item;
            }
        return ArrGPT3;

    }

    /**
     Performs an Merge sort on a String Array
     */

    private void mergeSort(String[] ArrGPT3) {
        if (ArrGPT3.length >= 2) {
            String[] left = new String[ArrGPT3.length / 2];
            String[] right = new String[ArrGPT3.length - ArrGPT3.length / 2];

            for (int i = 0; i < left.length; i++) {
                left[i] = ArrGPT3[i];
            }
            for (int i = 0; i < right.length; i++) {
                right[i] = ArrGPT3[i + ArrGPT3.length / 2];
            }
            mergeSort(left);
            mergeSort(right);
            merge(ArrGPT3, left, right);
        }
    }

    /**
     Merges two sub-arrays into one single array
     */

    public void merge(String[] ArrGPT3, String[] left, String[] right){
        int FirstIndex = 0;
        int SecondIndex = 0;
        for (int i = 0; i < ArrGPT3.length; i++){
            if (SecondIndex >= right.length || (FirstIndex < left.length && left[FirstIndex].compareToIgnoreCase(right[SecondIndex])<0)) {
                ArrGPT3[i] = left[FirstIndex];
                FirstIndex++;
            }
            else {
                ArrGPT3[i] = right[SecondIndex];
                setSwaps(getSwaps() + 1);
                SecondIndex++;
            }
        }
    }

    /**
     Takes the time before both of the sorts and after both of the sorts then print out how long it has taken.
     */

    public void Measure(ArrayList GPT3Ins, String[] GPT3Merge, int words){
            //From here 100 items sort
            long startTimeIns = System.currentTimeMillis();
            String[] Sorted = insertionSort(GPT3Ins);
            long stopTimeIns = System.currentTimeMillis();
            long sortTime = stopTimeIns - startTimeIns;
            System.out.println("It takes " + Long.toString(sortTime) + " Milliseconds to insertion sort " + words + " Words and in " + Integer.toString(getSwaps()) + " Swaps");
            setSwaps(0);
            //for (int g = 0; g < WordGPT3.length; g++) {
                //System.out.println(WordGPT3[g]);
            //}
            long sortTimeMer;
            long startTimeMer = System.currentTimeMillis();
            mergeSort(GPT3Merge);
            long stopTimeMer = System.currentTimeMillis();
            sortTimeMer = stopTimeMer - startTimeMer;
            //for (int f = 0; f < WordGPT3.length; f++) {
                //System.out.println(WordGPT3[f]);
            //}
            System.out.println("It takes " + Long.toString(sortTimeMer) + " Milliseconds to merge sort " + words + " Words and in " + Integer.toString(getSwaps()) + " Swaps");
            setSwaps(0);
            System.out.println();
            System.out.println();
        }
    }


