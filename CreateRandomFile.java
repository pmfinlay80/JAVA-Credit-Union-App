//Programmer: Pauline Finlay
//Student No: B00107945
//Date: 15.03.2018
//Assignment 2
//This section will create a random access file and will write 100 empty records to file.

import java.io.*;

public class CreateRandomFile
 {
  private Record blank;
  private RandomAccessFile file;

  public CreateRandomFile()

   {
    blank = new Record();

  try
   {

    file = new RandomAccessFile( "credit.dat", "rw" );

    for (int i=0; i<100; i++)
    blank.write( file );

   }

  catch(IOException e)

  {

   System.err.println("File not opened properly\n" + e.toString() );

   System.exit( 1 );

  }
}

}
