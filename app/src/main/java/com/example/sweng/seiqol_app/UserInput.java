package com.example.sweng.seiqol_app;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class UserInput extends AppCompatActivity  {
    ArrayList<String> data = new ArrayList<>();
    Intent i;
    String desc0, desc1, desc2, desc3, desc4;
    boolean descFill=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1_cues);
        EditText et = (EditText)findViewById(R.id.FirstArea);
        et.requestFocus();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            data = extras.getStringArrayList("DATA");
            if(data.size()<4) {
                //data.add(3, "");
                //data.add(4, "");
                //data.add(5, "");
                //data.add(6, "");
                //data.add(7, "");
            }
            else
            {
                et = (EditText)findViewById(R.id.FirstArea);
                et.setText(data.get(8));
                et = (EditText)findViewById(R.id.SecondArea);
                et.setText(data.get(9));
                et = (EditText)findViewById(R.id.ThirdArea);
                et.setText(data.get(10));
                et = (EditText)findViewById(R.id.FourthArea);
                et.setText(data.get(11));
                et = (EditText)findViewById(R.id.FifthArea);
                et.setText(data.get(12));
                desc0=data.get(3);
                desc1=data.get(4);
                desc2=data.get(5);
                desc3=data.get(6);
                desc4=data.get(7);

            }
        }


        Button cb1 = (Button) findViewById(R.id.description0);
        cb1.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       AlertDialog.Builder alert = new AlertDialog.Builder(UserInput.this);
                                       alert.setMessage("Enter label description");

                                       // Set an EditText view to get user input
                                       final EditText input = new EditText(UserInput.this);
                                       alert.setView(input);
                                       if(desc0!=null){input.setText(desc0);}

                                       alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                           public void onClick(DialogInterface dialog, int whichButton) {

                                               // Do something with value!
                                               data.set(3, input.getText().toString());
                                               desc0=input.getText().toString();
                                           }
                                       });

                                       alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                           public void onClick(DialogInterface dialog, int whichButton) {
                                               // Canceled.
                                           }
                                       });

                                       alert.show();
                                   }
                               });
            Button cb2 = (Button) findViewById(R.id.description1);
            cb2.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           AlertDialog.Builder alert = new AlertDialog.Builder(UserInput.this);
                                           alert.setMessage("Enter label description");
                                           // Set an EditText view to get user input
                                           final EditText input = new EditText(UserInput.this);
                                           alert.setView(input);
                                           if(desc1!=null){input.setText(desc1);}
                                           alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                               public void onClick(DialogInterface dialog, int whichButton) {

                                                   // Do something with value!
                                                   data.set(4, input.getText().toString());
                                                   desc1=input.getText().toString();
                                               }
                                           });

                                           alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                               public void onClick(DialogInterface dialog, int whichButton) {
                                                   // Canceled.
                                               }
                                           });

                                           alert.show();
                                       }
                                   });
        Button cb3 = (Button) findViewById(R.id.description2);
        cb3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            AlertDialog.Builder alert = new AlertDialog.Builder(UserInput.this);
                                            alert.setMessage("Enter label description");
                                            // Set an EditText view to get user input
                                            final EditText input = new EditText(UserInput.this);
                                            alert.setView(input);
                                            if(desc2!=null){input.setText(desc2);}
                                            alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {

                                                    // Do something with value!
                                                    data.set(5, input.getText().toString());
                                                    desc2=input.getText().toString();
                                                }
                                            });

                                            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {
                                                    // Canceled.
                                                }
                                            });

                                            alert.show();
                                        }
                                    });
        Button cb4 = (Button) findViewById(R.id.description3);
        cb4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            AlertDialog.Builder alert = new AlertDialog.Builder(UserInput.this);
                                            alert.setMessage("Enter label description");
                                            // Set an EditText view to get user input
                                            final EditText input = new EditText(UserInput.this);
                                            alert.setView(input);
                                            if(desc3!=null){input.setText(desc3);}
                                            alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {

                                                    // Do something with value!
                                                    data.set(6, input.getText().toString());
                                                    desc3=input.getText().toString();
                                                }
                                            });

                                            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {
                                                    // Canceled.
                                                }
                                            });

                                            alert.show();
                                        }
                                    });
        Button cb5 = (Button) findViewById(R.id.description4);
        cb5.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            AlertDialog.Builder alert = new AlertDialog.Builder(UserInput.this);
                                            alert.setMessage("Enter label description");
                                            // Set an EditText view to get user input
                                            final EditText input = new EditText(UserInput.this);
                                            alert.setView(input);
                                            if(desc4!=null){input.setText(desc4);}
                                            alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {

                                                    // Do something with value!
                                                    data.set(7, input.getText().toString());
                                                    desc4=input.getText().toString();
                                                }
                                            });

                                            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {
                                                    // Canceled.
                                                }
                                            });

                                            alert.show();
                                        }
                                    });

        Button next = (Button) findViewById(R.id.next_0);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.set(8, ((EditText)findViewById(R.id.FirstArea)).getText().toString());
                data.set(9, ((EditText)findViewById(R.id.SecondArea)).getText().toString());
                data.set(10, ((EditText)findViewById(R.id.ThirdArea)).getText().toString());
                data.set(11, ((EditText)findViewById(R.id.FourthArea)).getText().toString());
                data.set(12, ((EditText)findViewById(R.id.FifthArea)).getText().toString());
                if(data.get(8).length() > 1 && data.get(9).length() > 1 && data.get(10).length() > 1 && data.get(11).length()>1 && data.get(12).length()>1) {
                    i = new Intent(UserInput.this, Screen2SampleBar.class);
                    i.putExtra("DATA", data);
                    startActivity(i);
                }
                else
                {
                    displayError(v);
                }
            }
        });

        Button back = (Button) findViewById(R.id.back_0);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(UserInput.this, Screen0EnterId.class);
                startActivity(i);
            }
        });



    }

    public void displayError(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String dMessage = "Please enter 5 areas.";
        builder.setMessage(dMessage);
        builder.setTitle("Invalid input");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}