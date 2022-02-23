package in.icomputercoding.computercoding.Languages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import in.icomputercoding.computercoding.PdfViewer;
import in.icomputercoding.computercoding.R;
import in.icomputercoding.computercoding.databinding.ActivityCppscreenBinding;
import in.icomputercoding.computercoding.databinding.ActivityHtml5ScreenBinding;

public class HTML5Screen extends AppCompatActivity {

    ActivityHtml5ScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHtml5ScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] pdfFiles = {"Chapter 1", "Chapter 2", "Chapter 3", "Chapter 4", "Chapter 5", "Chapter 6", "Chapter 7"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pdfFiles) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return super.getView(position, convertView, parent);
            }
        };


        binding.PdfList.setAdapter(adapter);

        binding.PdfList.setOnItemClickListener((parent, view, position, id) -> {
            String item = binding.PdfList.getItemAtPosition(position).toString();

            Intent i = new Intent(HTML5Screen.this, PdfViewer.class);
            i.putExtra("HTML5Programming", item);
            startActivity(i);
        });

    }
}