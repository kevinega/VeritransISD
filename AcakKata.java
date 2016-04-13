import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Kevin Ega Pratama on 4/13/2016.
 * kega.pratama@gmail.com +6282111981741
 */
public class AcakKata {

    /*
        Class Shuffl
     */
    public static class Shuffle {
        /*
         *  Method Shuffle This mengacak character dari sebuah String input
         *  kemudian mengembalikan String dengan character yang sudah diacak
         */
        public String shuffle(String input) {
            List<Character> characters = new ArrayList<Character>();
            for (char c : input.toCharArray()) {
                characters.add(c);
            }
            StringBuilder output = new StringBuilder(input.length()); //buat nge build String output
            //Melakukan shuffle character secara random kemudian build string output
            while (characters.size() != 0) {
                int randPicker = (int) (Math.random() * characters.size());
                output.append(characters.remove(randPicker));
            }
            String outputnya = (output.toString());
            return outputnya;
        }
    }
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        String FileSoal;

        //Check status main
        boolean masihMain = true;
        while(masihMain) {
            //Saat di fase bermain pemain diawal diminta memilih soal dengan memasukkan input integer
            masihMain = true;
            System.out.println("Pilih Soal : (1) 3 Soal; (2) 5 Soal, (3) 10 Soal");
            System.out.print("Pilihan anda = ");
            int noSoal = Integer.parseInt(in.nextLine());
            System.out.println();
            FileSoal = "soal"+noSoal+".txt"; //initialize question

            try {
                Shuffle s = new Shuffle(); //initialize object Shuffle
                ArrayList<String> soalBaca = new ArrayList<String>(); //buat menyimpan soal asli (sebelum diacak)
                ArrayList<String> soalRandomized = new ArrayList<String>(); //buat menyimpan soal setelah diacak
                String line;

                //membaca file soal dan memasukkan soal-soal ke arrayList soalBaca
                FileReader fileReader = new FileReader(FileSoal);
                BufferedReader bufferedReader;
                bufferedReader = new BufferedReader(fileReader);
                while ((line = bufferedReader.readLine()) != null) {
                    soalBaca.add(line);
                }

                //ambil soal dari ArrayList lakukan Shuffle kemudian masukkan kedalam ArrayList soalRandomized
                String baca;
                String hasil;
                for (int i = 0; i < soalBaca.size(); i++) {
                    baca = soalBaca.get(i);
                    if (!baca.equals("")) {
                        hasil = s.shuffle(baca);
                        soalRandomized.add(hasil);
                    }
                }
                //proses permainan
                boolean jawaban; //flag untuk jawaban benar atau salah
                for (int i = 0; i < soalRandomized.size(); i++) {
                    jawaban = false; //initialize flag
                    System.out.println("Tebak kata: " + soalRandomized.get(i)); //keluarin soal

                    //Akan looping sampai jawaban benar, sehingga jika jawaban salah bisa try again
                    while (!jawaban) {
                        System.out.print("Jawab: ");
                        String jawab = in.nextLine(); //player input jawaban
                        if (jawab.equals(soalBaca.get(i))) { //kalau benar
                            jawaban = true;
                            System.out.println("BENAR!");
                            if (i == (soalRandomized.size() - 1)) { //kalau sudah nomer terakhir
                                System.out.println("SELAMAT ANDA MENJAWAB SEMUA SOAL DENGAN BENAR");
                                //tanya player apakah mau main lagi
                                System.out.println("Play Again ? (Y/N)");
                                if(in.nextLine().equalsIgnoreCase("Y")) {
                                    masihMain=true;} //kalau mau lanjut main
                                else {
                                    masihMain=false; //kalau tidak mau lanjut main
                                    System.out.println("Terimakasih karena telah bermain! :3");
                                }
                            }
                        } else {
                            System.out.println("SALAH! Silahkan coba lagi!"); //kalau jawaban salah
                        }

                    }
                }

            } catch (FileNotFoundException ex) {
                System.out.println("Unable to open file '" + FileSoal + "'");
            } catch (IOException x) {
                System.out.println("Error Reading file '" + FileSoal + "'");

            }
        }


    }



    }
