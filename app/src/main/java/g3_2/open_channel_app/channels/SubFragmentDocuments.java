package g3_2.open_channel_app.channels;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import g3_2.open_channel_app.R;


public class SubFragmentDocuments extends Fragment{

    MaterialButton buttonDownloadDoc;
    String pdfLink = "http://unec.edu.az/application/uploads/2014/12/pdf-sample.pdf";


    //ToDo implement Document fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.subfragment_document, container, false);

        //get reference to ids
        buttonDownloadDoc = rootView.findViewById(R.id.buttonDownloadDoc);

        //if download button pressed
        buttonDownloadDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //brings to chrome and then downloads and previews pdf
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(pdfLink), "text/html");
                startActivity(intent);


            }
        });



        return rootView;
    }


}

