import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.lang.*;
class Sort{
	//The file is encoded in "UTF-8"
	static final Charset charset = Charset.forName("UTF-8");
	static int COUNT=0;
	static int LENGTH=0;
	static final int MAX=Integer.MAX_VALUE;
	// create an array list
	List<Integer> arrayList = new ArrayList<Integer>();
	List<Integer> arrayListSource = new ArrayList<Integer>();
	List<Integer> arrayListSorted = new ArrayList<Integer>();
	File f = null;
	// solution file name
	String fileName ="";

	//
	//insertionSort
	public void insertionSort(Path source,Path result){
		//creat insertionSort Directory
		fileName="insertionSort";
		f = new File(result.toString(),fileName);
		createDirectory(f.toPath());	
			
        //get source file's name without extention
        fileName = source.getFileName().toString();
		int pos = fileName.lastIndexOf(".");
		if (pos > 0 && pos < (fileName.length() - 1)) { // If '.' is not the first or last character.
				fileName = fileName.substring(0, pos);
				System.out.println(fileName);

		}//if

		//read source arrayList
		arrayListSource.clear();
		arrayListSource=readFile(source);
		//System.out.println("content: "+readFile(source));
		//copy arraylist
		arrayListSorted.clear();
		arrayListSorted.addAll(arrayListSource);

		LENGTH = arrayListSorted.size();
		System.out.println("lengthA = "+ LENGTH);

		int count = 0;
		int key;
		int i;
		for(int j=2;j<= LENGTH;j++){
			//arraylist index start from 0
			key = arrayListSorted.get(j-1);
			i=j-1;
			while(i>0 && arrayListSorted.get(i-1)>key){
				count++;
				arrayListSorted.set(i+1-1,arrayListSorted.get(i-1));
				i--;
			}//while
			arrayListSorted.set(i+1-1,key);


		}//for j
		System.out.println("insertionSort_count = "+count);
		COUNT=count;
		//System.out.println("insertionSort: "+arrayListSorted);
		
		//creeat file for each num file
		f = new File(f,fileName+"_insertionSort.txt");
		//System.out.println("result = "+f.toPath());
		writeFile(arrayListSorted,f.toPath());

	}//insertionSort


	public void mergeSort(Path source, Path result){
		COUNT=0;
		fileName="MergeSort";
		f = new File(result.toString(),fileName);
		createDirectory(f.toPath());
		//read source arrayList
		arrayListSource.clear();
		arrayListSource=readFile(source);

		LENGTH=arrayListSource.size();
		//System.out.println(arrayListSource);
		//arratlist index start from 0
		mergeSort(arrayListSource,0,arrayListSource.size()-1);
		System.out.println("mergeSort_count= "+ COUNT);
		getName(source);
		f = new File(f,fileName+"_mergeSort.txt");
		writeFile(arrayListSource,f.toPath());


	}//mergeSort(Path source, Path result)


	public void mergeSort(List<Integer> arrayList,int p,int r){
		if(p<r){
			int q = (p+r)/2;
			mergeSort(arrayList, p, q);
			mergeSort(arrayList, q+1, r);
			merge (arrayList, p, q, r);

		}

	}//mergeSort((List<Integer> arrayList,int p,int r))

	public void merge(List<Integer> arrayList, int p, int q, int r){
		// Find sizes of two subarrays to be merged
		int n1 = q-p+1;
		int n2 = r-q;
		 /* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];

		 /*Copy data to temp arrays*/
        for (int i=0; i<n1; i++)
            L[i] = arrayList.get(p + i);
        for (int j=0; j<n2; j++)
            R[j] = arrayList.get(q + 1+ j);
        //System.out.println(Arrays.toString(L));
        //System.out.println(Arrays.toString(R));
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = p;
        while (i < n1 && j < n2){
        	COUNT++;
            if (L[i] <= R[j])
            {
                arrayList.set(k,L[i]);
                i++;
            }
            else
            {
                arrayList.set(k,R[j]);
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1){	
        	COUNT++;
        	arrayList.set(k,L[i]);
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2){	
        	COUNT++;
            arrayList.set(k,R[j]);
            j++;
            k++;
        }





	}//merge
	//
	//heapSort
	public void heapSort(Path source, Path result){
		COUNT=0;
		fileName="heapSort";
		f = new File(result.toString(),fileName);
		createDirectory(f.toPath());
		//read source arrayList
		arrayListSource.clear();
		arrayListSource=readFile(source);
		LENGTH=arrayListSource.size();

		heapSort(arrayListSource);
		System.out.println("heapSort_count= "+ COUNT);
		getName(source);
		f = new File(f,fileName+"_heapSort.txt");
		writeFile(arrayListSource,f.toPath());

	}

	public void heapSort(List<Integer> arrayList){
	        int n = arrayList.size();
	 		int temp=0;
	        // Build Max heap (rearrange array)
	        for (int i = n / 2 - 1; i >= 0; i--)
	            heapify(arrayList, n, i);
	 
	        // One by one extract an element from heap
	        for (int i=n-1; i>=0; i--)
	        {
	            // Move current root to end
	            temp = arrayList.get(0);
	            arrayList.set(0,arrayList.get(i));  // arr[0] = arr[i];
	            arrayList.set(i,temp);		//arr[i] = temp;
	 
	            // call max heapify on the reduced heap
	            heapify(arrayList, i, 0);
	        }
	    }
	 // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    public void heapify(List<Integer> arrayList, int n, int i){
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2
 
        // If left child is larger than root
        if (l < n && arrayList.get(l) > arrayList.get(largest))
            largest = l;
 
        // If right child is larger than largest so far
        if (r < n && arrayList.get(r) > arrayList.get(largest))
            largest = r;
 
        // If largest is not root
        if (largest != i)
        {
            int swap = arrayList.get(i); //int swap = arr[i];
            arrayList.set(i,arrayList.get(largest));	//arr[i] = arr[largest];
            arrayList.set(largest,swap);	//arr[largest] = swap;
 			COUNT++;
            // Recursively heapify the affected sub-tree
            heapify(arrayList, n, largest);
        }
    }//heapify

    public void quickSort(Path source, Path result){

    	COUNT=0;
		fileName="quickSort";
		f = new File(result.toString(),fileName);
		createDirectory(f.toPath());
		//read source arrayList
		arrayListSource.clear();
		arrayListSource=readFile(source);
		int lengthA=arrayListSource.size();
		LENGTH=lengthA;

		quickSort(arrayListSource,0,lengthA-1);
		System.out.println("quickSort_count= "+ COUNT);
		getName(source);
		f = new File(f,fileName+"_heapSort.txt");
		writeFile(arrayListSource,f.toPath());
    }//quickSort

    public void quickSort(List<Integer> arrayList ,int p ,int r){//p->low index r->high index
    	if(p<r){
    		///* q is partitioning index, arr[q] is now at right place */
    		int q = partition(arrayList, p, r);
    		quickSort(arrayList,p,q-1);
    		quickSort(arrayList,q+1,r);
    	}

    }//quickSort

    /* This function takes last element as pivot,
       places the pivot element at its correct
       position in sorted array, and places all
       smaller (smaller than pivot) to left of
       pivot and all greater elements to right
       of pivot */
    public int partition(List<Integer> arrayList, int p, int r){
    	int x = arrayList.get(r);//x is pivot 
    	int i = p-1;
    	int temp;
    	for(int j=p;j<r;j++){
    		COUNT++;
    		if(arrayList.get(j)<x){
    			i++;
    			temp= arrayList.get(i);
    			arrayList.set(i,arrayList.get(j));
    			arrayList.set(j,temp);
    		}//if

    	}//for
    	 // swap arr[i+1] and  pivot
    	temp=arrayList.get(i+1);
    	arrayList.set(i+1,arrayList.get(r));
    	arrayList.set(r,temp);
    	return i+1;

    }//partition

    public void countingSort(Path source,Path result){
    	COUNT=0;
		fileName="countingSort";
		f = new File(result.toString(),fileName);
		createDirectory(f.toPath());
		//read source arrayList
		arrayListSource.clear();
		arrayListSource=readFile(source);
		//clear result arrayList
		arrayListSorted.clear();
		//copy arrayListSource
		arrayListSorted.addAll(arrayListSource);
		int lengthA=arrayListSource.size();
		LENGTH=lengthA;
		//key is the largest number in array(data range 0-key)
		int key= arrayListSource.get(0);
		for(int max:arrayListSource){
			if(max>key){
				key=max;
			}


		}
		//System.out.println("key= "+key);
		countingSort(arrayListSource,arrayListSorted,key);
		System.out.println("countingSort_count= "+ COUNT);
		getName(source);
		f = new File(f,fileName+"_countingSort.txt");
		writeFile(arrayListSorted,f.toPath());
			

    }//countingSort

    public void countingSort(List<Integer> source,List<Integer> result, int k){
    	//creat count array(index 0 - k)
    	int[] index = new int[k+1];
    	for(int i=0;i<=k;i++){index[i]=0;}
    	//1) Take a count array to store the count of each unique object.
    	for(int n:source){
    		index[n]++;
    	}
    	
    	//2) Modify the count array such that each element at each index 
  		//stores the sum of previous counts.
  		for(int i=1;i<=k;i++){
  			index[i]=index[i]+index[i-1];
  		}
  		//System.out.println(Arrays.toString(index));
  		//3) Output each object from the input sequence followed by decreasing its count by 1.
  		for(int i=LENGTH-1;i>=0;i--){
  			//arrayList start from 0
  			int n=arrayListSource.get(i);
  			arrayListSorted.set(index[n]-1,n);
  			index[n]--;
  			COUNT++;
  		}
  		


    }//countingSort

    //radixSort
    public void radixSort(Path source,Path result){
    	COUNT=0;
		fileName="radixSort";
		f = new File(result.toString(),fileName);
		createDirectory(f.toPath());
		//read source arrayList
		arrayListSource.clear();
		arrayListSource=readFile(source);
		//clear result arrayList
		arrayListSorted.clear();
		//copy arrayListSource
		arrayListSorted.addAll(arrayListSource);
		int lengthA=arrayListSource.size();
		LENGTH=lengthA;
		int max = getMax(arrayListSource);
		// Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
		for(int exp=1;max/exp>0;exp*=10){
			radixSort(arrayListSource,exp);
			arrayListSource.clear();
			arrayListSource.addAll(arrayListSorted);
		}

		
		System.out.println("radixSort_count= "+ COUNT);
		getName(source);
		f = new File(f,fileName+"_radixSort.txt");
		writeFile(arrayListSorted,f.toPath());

    }//radixSort

    public void radixSort(List<Integer> arrayList, int exp){
    	int index[]=new int[10];
    	Arrays.fill(index,0);
    	for(int n:arrayList){
    		index[(n/exp)%10]++;
    	}

    	for(int i=1;i<10;i++){
    		index[i]=index[i-1]+index[i];
    	}

    	for(int i=LENGTH-1;i>=0;i--){
    		int n=arrayListSource.get(i);
    		arrayListSorted.set(index[(n/exp)%10]-1,n);
    		index[(n/exp)%10]--;
    		COUNT++;
    	}
    	//System.out.println(arrayListSorted);

    }//radixSort

    public void bucketSort(Path source, Path result){
    	COUNT=0;
		fileName="bucketSort";
		f = new File(result.toString(),fileName);
		createDirectory(f.toPath());
		//read source arrayList
		arrayListSource.clear();
		arrayListSource=readFile(source);
		
		LENGTH=arrayListSource.size();
		bucketSort(arrayListSource);

		//System.out.println(arrayListSource);
		System.out.println("bucketSort_count= "+ COUNT);
		getName(source);
		f = new File(f,fileName+"_bucketSort.txt");
		writeFile(arrayListSource,f.toPath());

    }//bucketSort

    public void bucketSort(List<Integer> arrayList){
    	int max=getMax(arrayList);
    	int min=getMin(arrayList);
    	int[] counters = new int[max - min + 1];
		for (int k : arrayList) {
		counters[k - min]++;
		}
		int i = 0;
		for (int j = 0; j < counters.length; j++) {
			for (int k = 0; k < counters[j]; k++) {
			
			arrayList.set(i,j+min);
			i++;
			COUNT++;
			}//for-k
		}//for-j


    }


	//read content from a text file return arratlist
	public List<Integer> readFile(Path source){
		try (BufferedReader reader = Files.newBufferedReader(source, charset)) {
    		String line = null;
    		arrayList.clear();
    		while ((line = reader.readLine()) != null && line.matches("[0-9]+") ) {
        		//System.out.println(line);
        		arrayList.add(Integer.parseInt(line));

    		}
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		return arrayList;

	}//readFile

	public void writeFile(List<Integer> arrayList, Path result){
		String s =  "lengthA= "+LENGTH+"\n"+
					"count= "+COUNT+"\n"+
					arrayList.toString();
		try (BufferedWriter writer = Files.newBufferedWriter(result, charset)) {
		    writer.write(s, 0, s.length());
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}


	}//writeFile

	public void createDirectory(Path path){
		try{
		if(!Files.exists(path)){
			Files.createDirectory(path);
			System.out.println("Directory is created!");
		}else{
			//System.out.println("Directory is exists: "+ path);
		}
		}//try
		catch(IOException e){
			System.out.println(e);
		}	

	}//createDirectory

	public String getName(Path source){
		fileName = source.getFileName().toString();
		int pos = fileName.lastIndexOf(".");
		if (pos > 0 && pos < (fileName.length() - 1)) { // If '.' is not the first or last character.
				fileName = fileName.substring(0, pos);
				//System.out.println(fileName);

		}//if
		return fileName;

	}//getName

	public int getMax(List<Integer> arrayList){
		int max=arrayList.get(0);
		for(int n:arrayList){
			if(n>max)
				max=n;
		}//for
		return max;

	}//getMax

	public int getMin(List<Integer> arrayList){
		int min=arrayList.get(0);
		for(int n:arrayList){
			if(n<min)
				min=n;
		}//for
		return min;

	}//getMin
	
}