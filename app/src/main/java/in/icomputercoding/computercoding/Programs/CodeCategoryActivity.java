package in.icomputercoding.computercoding.Programs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import in.icomputercoding.computercoding.R;
import in.icomputercoding.computercoding.databinding.ActivityCodeCategoryBinding;

public class CodeCategoryActivity extends AppCompatActivity {

    String[] list;
    ActivityCodeCategoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCodeCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadCategory();

        CustomAdapter adapter = new CustomAdapter();
        binding.icPrograms.setAdapter(adapter);
        binding.icPrograms.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(CodeCategoryActivity.this, CodeActivity.class);
            i.putExtra("position", list[position]);
            startActivity(i);
        });


    }

    private void loadCategory() {

        final String[] c_programs = {"Write a program to find length of string.",
                "Write a program to add two matrices using dynamic allocation method.",
                "Write a program to find length of a string using arrays.",
                "Write a program using arrays to check given string is palindrome or not.",
                "Write a program to copy one string to another string using arrays.",
                "Write a program to copy one string to another string using pointers.",
                "Write a program for nested structure.",
                "Write a program to convert string in uppercase without library function using pointers.",
                "Program to accept an integer and check if it is even or odd.",
                "Accept a character from the user and check whether the character is a vowel or consonant."};


        final String[] cpp_programs = {"Write a  CPP program to check whether number is Even or Odd.",
                "Write a CPP Program to Reverse an Integer.",
                "Write a CPP Program to Find Fibonacci Series up to n number of terms.",
                "Write a CPP Program to Check if a year is leap year or not.",
                "Write a CPP Program to Display multiplication table up to a given range.",
                "Write a CPP Program for implementation of Quick Sort using array.",
                "Write a CPP Program to Swap two numbers using call by reference.",
                "Write a CPP Program to calculate Volume of Cube using constructor.",
                "Write a CPP Program to Maintain Book Records using File Handling.",
                "Write a CPP Program to Add two numbers using function template."};

        final String[] java_programs = {"Write a Java program to perform basic Calculator operations.",
                "Write a Java program to calculate a Factorial of a number.",
                "Write a Java program to calculate Fibonacci Series up to n numbers.",
                "Write a Java program to find out whether the given String is Palindrome or not.",
                "Write a Java program to calculate Permutation and Combination of 2 numbers.",
                "Write a Java Program to reverse the letters present in the given String.",
                "Write a Java Program to check whether the given array is Mirror Inverse or not.",
                "Write a Java program to implement a Binary Search Algorithm.",
                "Write a Java program to connect to a SQL DataBase.",
                "Write a Java program to remove elements from an ArrayList."};


        final String[] HTML5_programs = { "Use CSS to set the background color of the document (body) to yellow.",
                "Create an HTML5 page which will display names of your family member on separate lines in different sizes. State size of each line in its text.",
                "Create an HTML5 page containing the polynomial expression as follows ao+a1x+a2x2+a3x3.",
                "1. Create an HTML5 page with following specifications \n" +
                "a. Title should be about MYCOLLEGE \n" +
                "b. Put the windows Logo image in the background \n " +
                "c. Place your College name at the to the page in large text followed by address in smaller size \n " +
                "d. Add names of courses offered each in a different color,style and typeface \n " +
                "e. Add scrolling text with a message of your choice " +
                "f. Add college image at the bottom \n " +
                "(HINT:Use appropriate header and footer for the page)",
                "Write a HTML code,which generate the following output",
                "Gives us following web page, with the New York Times embedded inside the blue frame:",
                "Write the HTML5 code which generate the following output"

        };




        String category = getIntent().getStringExtra("codeCategory");
        switch (category) {
            case "c_programs":
                list = c_programs;
                break;
            case "cpp_programs":
                list = cpp_programs;
                break;
            case "java_programs":
                list = java_programs;
                break;
            case "HTML5_programs":
                list = HTML5_programs;
                break;
        }

    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint({"ViewHolder", "InflateParams"})
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.code_category_layout, null);
            TextView textView = convertView.findViewById(R.id.program1);
            textView.setText(list[position]);
            return convertView;
        }
    }

}