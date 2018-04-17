import java.io.*;
import java.nio.file.*;

class ReadFile{
	File[] allSubFiles = null;
	//file f is solution file
	File f = null;
	String currentPath="";
	//get current path
    static final Path PATH = Paths.get("");
    
	public File[] list_Files(String subDirectory){
		try {
		    //print real path
		    
		    //System.out.println(path.toRealPath());
		    currentPath = PATH.toRealPath().toString();
		    System.out.println("currentPath: "+PATH.toRealPath());
		    //go to subDirectory
			f=new File(currentPath,subDirectory);
			allSubFiles=f.listFiles();

			//creat Solutions subDirectory
			f = new File(currentPath,subDirectory+"Solutions");
			
			if (!f.exists()) {
	            if (f.mkdir()) {
	                System.out.println("Directory is created!");
	                System.out.println("solutionsPath: "+f.toPath());
	            } else {
	                System.out.println("Failed to create directory!");
	            }
        	}
			
        	

		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n");
		    // Logic for case when file doesn't exist.
		} catch (IOException x) {
		    System.err.format("%s%n", x);
		    // Logic for other sort of file error.
		}
		return allSubFiles;

	}//list_Files

	public void partOne(File[] allSubFiles) throws IOException{
		
		//create Sort
    	Sort sort = new Sort();
    	Path path1=null;
    	Path path2=f.toPath();

			for (File file : allSubFiles) {
				//check the file type = txt?
			    if(Files.probeContentType(file.toPath()).equals("text/plain"))
			    {	System.out.println("------------------------------------");
			        System.out.println(file.getAbsolutePath()+" is text file");
			        //Steps for directory
			      
			        path1=file.toPath();
			        
			        sort.insertionSort(path1,path2);
			        sort.mergeSort(path1,path2);
			        sort.heapSort(path1,path2);
			        sort.quickSort(path1,path2);

			    }
			    else
			    {	System.out.println("type:" + Files.probeContentType(file.toPath()));
			        System.out.println(file.getAbsolutePath()+" is not text file");
			        //steps for files
			    }
			}


	}//partOne

	public void partTwo(File[] allSubFiles) throws IOException{
		
		//create Sort
    	Sort sort = new Sort();
    	Path path1=null;
    	Path path2=f.toPath();

			for (File file : allSubFiles) {
				//check the file type = txt?
			    if(Files.probeContentType(file.toPath()).equals("text/plain")){
			    	System.out.println("------------------------------------");
			        System.out.println(file.getAbsolutePath()+" is text file");
			        //Steps for directory
			      
			        path1=file.toPath();
			        sort.countingSort(path1,path2);
			        sort.radixSort(path1,path2);
			        sort.bucketSort(path1,path2);
			    }
			    else
			    {	System.out.println("type:" + Files.probeContentType(file.toPath()));
			        System.out.println(file.getAbsolutePath()+" is not text file");
			        //steps for files
			    }
			}


	}//partTwo

}