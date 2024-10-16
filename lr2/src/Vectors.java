import javax.lang.model.type.NullType;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Vectors {
    double[] v;
    Vectors(int n)
    {
        Random random = new Random();
        this.v = new double[n];

    }
    public void AddCoordinates(String[] coordinates)
    {
        for (int i = 0; i < v.length; i++) {

            v[i] = Double.parseDouble(coordinates[i]);
        }
    }
    public void AddRandCoordinates(){
        Random random = new Random();
        for (int i = 0; i < v.length; i++) {
            v[i] = random.nextDouble(-100, 100);
        }
    }
    public String ToString()
    {
        String v_str = "";
        for (int i = 0; i < v.length;i++)
        {
            v_str += (Double.toString(v[i]) + " ");
        }
        return v_str;
    }
    public Double GetEl(String index)
    {
        try
        {
            int id = Integer.parseInt(index);
            return v[id];
        }
        catch (Exception ex)
        {
            System.out.println("Такого индекса не существует!");
            return null;
        }
    }
    public Double GetEl(int index)
    {
        try
        {
            return v[index];
        }
        catch (Exception ex)
        {
            System.out.println("Такого индекса не существует!");
            return null;
        }
    }
    public void ChangeEl(String index, Double new_value)
    {
        try{
            int id = Integer.parseInt(index);
            v[id] = new_value;
        }
        catch (Exception ex)
        {
            System.out.println("Такого индекса не существует!");
        }
    }
    public void ChangeEl(int index, Double new_value)
    {
        try{
            v[index] = new_value;
        }
        catch (Exception ex)
        {
            System.out.println("Такого индекса не существует!");
        }
    }
    public int Length()
    {
        int len = 0;
        for (int i = 0; i < v.length; i++)
        {
            len++;
        }
        return len;
    }
    public Double FindMin()
    {
        double min = v[0];
        for (double value : v) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }
    public Double FindMax()
    {
        double max = v[0];
        for (double value : v) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
    // Swaps two elements
    void swap(double[] array, int i, int j)
    {
        double temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    /*  Takes last element as pivot,
        moves the pivot element to the correct position,
        and places all smaller to the left
        all greater elements to the right
    */
    int partition(double[] array, int left, int right)
    {
        // Choose the pivot element
        double pivot = array[right];


        // The correct position of the pivot found so far
        int i = (left - 1);


        for (int j = left; j <= right - 1; j++) {


            // If current element is smaller than the pivot
            if (array[j] < pivot) {


                // Swap two elements
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, right);
        return (i + 1);
    }


    // Partitions the array and sorts its parts
    void quickSort(double[] array, int left, int right)
    {
        if (left < right) {


            // Move the pivot to the correct position
            int partitioningIndex = partition(array, left, right);


            // Sort elements before
            quickSort(array, left, partitioningIndex - 1);
            // Sort elements after
            quickSort(array, partitioningIndex + 1, right);
        }
    }


    public void Sort(){
        quickSort(v, 0, v.length - 1);
    }
    public double Evclid()
    {
        double sum = 0;
        for (double value: v)
        {
            sum += value * value;
        }
        double norm = Math.sqrt(sum);
        return norm;
    }
    public void ValMul(double ch)
    {
        for (int i = 0; i < v.length; i++)
        {
            v[i] = v[i]*ch;
        }
    }
    public static Vectors VectorSum(Vectors v1, Vectors v2)
    {
        Vectors v = new Vectors(v1.Length());
        for (int i =0; i < v1.Length(); i++)
        {
            v.ChangeEl(i, v1.GetEl(i) + v2.GetEl(i));
        }
       return v;
    }
    public static double Scalar(Vectors v1, Vectors v2)
    {
        double scalar = 0;
        for (int i =0; i < v1.Length(); i++)
        {
           scalar += v1.GetEl(i) * v2.GetEl(i);
        }
        return scalar;
    }

}
