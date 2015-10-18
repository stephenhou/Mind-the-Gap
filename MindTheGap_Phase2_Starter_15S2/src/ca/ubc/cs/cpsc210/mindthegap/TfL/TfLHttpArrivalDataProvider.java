package ca.ubc.cs.cpsc210.mindthegap.TfL;

import android.util.Log;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Wrapper for TfL Arrival Data Provider
 */
public class TfLHttpArrivalDataProvider extends AbstractHttpDataProvider {
    private Station stn;

    public TfLHttpArrivalDataProvider(Station stn) {
        super();
        this.stn = stn;
    }

    @Override
    /**
     * Produces URL used to query TfL web service for expected arrivals at
     * station specified in call to constructor.
     *
     * @returns URL to query TfL web service for arrival data
     */
    protected URL getURL() throws MalformedURLException {
        String request = "";
        boolean lineNamUnempty = false;
        String lineNam="";
        for(Line next :stn.getLines()){
           String lineId = next.getId();
            if (lineNamUnempty){
                lineNam = lineNam + "," + lineId;
            }else{
                lineNam = lineId;
                lineNamUnempty=true;
            }

        }

        String id = stn.getID();

        request= "https://api.tfl.gov.uk/Line/" + lineNam +"/Arrivals?stopPointId="+ id +"&app_id=&app_key=";
        Log.e("DataProvider",request);



        // TODO Phase 2 Task 7

        return new URL(request);
    }
}
