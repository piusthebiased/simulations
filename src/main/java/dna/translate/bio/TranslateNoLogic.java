package dna.translate.bio;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class TranslateNoLogic {
    public static void main(String[] args) {
        //codeon table
        String[] protein = new String[]{"Phenylalanine", "Leucine", "ILE + Isoleucine", "Methionine (Start)", "Valine", "Serine", "Proline", "Threonine", "Alanine", "Tyrosine", "Stop", "Histidine", "GLN + Glutamine", "ASN + Asparagine", "Lysine", "Aspartic Acid", "Glutamic Acid", "Cysteine", "Stop", "TRP + Tryptophan", "Arginine", "Serine", "Arginine", "Glycine"};
        int[] split = new int[]{2, 6, 3, 1, 4, 4, 4, 4, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 4, 2, 2, 4};
        int[] prefix = new int[split.length];
        prefix[0] = split[0];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] += split[i] + prefix[i - 1];
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Input DNA Strand: ");
        String input = sc.nextLine();

        char[] c = input.toCharArray();
        int fp = c.length / 3;
        int mod = c.length % 3;
        String excess = "";
        for(int e = 0; e < mod; e++){
            excess += c[(3*fp) + e];
        }

        StringBuilder sb = new StringBuilder();
        StringBuilder p3 = new StringBuilder();
        char[] buffer = new char[3];
        for(int i = 0; i < fp; i++){
            for(int j = 0; j < 3; j++){
                buffer[j] = c[(i*3) + j];

                if(j == 2) {
                    int num = 0; //indexed at 1
                    num += 4 * (codonConv(buffer[0]) - 1);
                    num += 16 * (codonConv(buffer[1]) - 1);
                    num += codonConv(buffer[2]);

                    int count = 0;
                    while(num > prefix[count]){
                        count++;
                    }

                    sb.append(protein[count]).append(" | ");
                    p3.append(protein[count].toUpperCase(Locale.ROOT), 0, 3).append(" | ");

                    System.out.println(num);
                }
            }
        }

        if(excess.length() > 0){
            sb.append("+ ").append(excess);
            p3.append("+ ").append(excess);
        }


        System.out.println("FULL DNA: ");
        System.out.println("Finished DNA Protein Mapping: ");
        System.out.println(sb + "\n");
        System.out.println("STD MAPPING: ");
        System.out.println(p3);
    }

    private static int codonConv(char c){
        return c == 'G' ? 4 : c == 'A' ? 3 : c == 'C' ? 2 : 1;
    }
}
