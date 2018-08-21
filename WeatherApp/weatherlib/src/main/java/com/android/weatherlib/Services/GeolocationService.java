package com.android.weatherlib.Services;

/**
 * Created by Damien on 21/08/2018.
 */

// TODO
public class GeolocationService {

    /*
    public void LocalisationRegistration(final String C_Loc){

        class LocalisationRegistrationClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(MainActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), IdentifyActivity.class));
                finish();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("ClientLocalisation",params[0]);
                finalResult = httpParse.postRequest(hashMap, HttpURL);
                return finalResult;
            }
        }

        LocalisationRegistrationClass localisationRegistrationClass = new LocalisationRegistrationClass();
        localisationRegistrationClass.execute(C_Loc);
    }

    */
}
