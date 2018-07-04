/*
 * APIMATICCalculatorLib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package io.apimatic.examples.controllers;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import io.apimatic.examples.*;
import io.apimatic.examples.models.*;
import io.apimatic.examples.exceptions.*;
import io.apimatic.examples.http.client.HttpClient;
import io.apimatic.examples.http.client.HttpContext;
import io.apimatic.examples.http.request.HttpRequest;
import io.apimatic.examples.http.response.HttpResponse;
import io.apimatic.examples.http.response.HttpStringResponse;
import io.apimatic.examples.http.client.APICallBack;

public class SimpleCalculatorController extends BaseController {    
    //private static variables for the singleton pattern
    private static Object syncObject = new Object();
    private static SimpleCalculatorController instance = null;

    /**
     * Singleton pattern implementation 
     * @return The singleton instance of the SimpleCalculatorController class 
     */
    public static SimpleCalculatorController getInstance() {
        synchronized (syncObject) {
            if (null == instance) {
                instance = new SimpleCalculatorController();
            }
        }
        return instance;
    }

    /**
     * Calculates the expression using the specified operation.
     * @param    GetCalculateInput    Object containing request parameters
     * @return    Returns the void response from the API call 
     */
    public void getCalculateAsync(
                final GetCalculateInput input,
                final APICallBack<Double> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {
                //the base uri for api requests
                String _baseUri = Configuration.baseUri;

                //prepare query string for API call
                StringBuilder _queryBuilder = new StringBuilder(_baseUri);
                _queryBuilder.append("/{operation}");

                //process template parameters
                APIHelper.appendUrlWithTemplateParameters(_queryBuilder, new HashMap<String, Object>() {
                    private static final long serialVersionUID = 4774639502211932971L;
                    {
                        put( "operation", (input.getOperation() != null) ? input.getOperation().value() : null );
                    }});

                //process query parameters
                APIHelper.appendUrlWithQueryParameters(_queryBuilder, new HashMap<String, Object>() {
                    private static final long serialVersionUID = 5528826637787277581L;
                    {
                        put( "x", input.getX() );
                        put( "y", input.getY() );
                    }});
                //validate and preprocess url
                String _queryUrl = APIHelper.cleanUrl(_queryBuilder);

                //load all headers for the outgoing API request
                Map<String, String> _headers = new HashMap<String, String>() {
                    private static final long serialVersionUID = 5457413494767420607L;
                    {
                        put( "user-agent", "APIMATIC 2.0" );
                    }
                };

                //prepare and invoke the API call request to fetch the response
                final HttpRequest _request = getClientInstance().get(_queryUrl, _headers, null);

                //invoke the callback before request if its not null
                if (getHttpCallBack() != null)
                {
                    getHttpCallBack().OnBeforeRequest(_request);
                }

                //invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {

                            //invoke the callback after response if its not null
                            if (getHttpCallBack() != null)	
                            {
                                getHttpCallBack().OnAfterResponse(_context);
                            }

                            //handle errors defined at the API level
                            validateResponse(_response, _context);

                            //extract result from the http response
                            String _responseBody = ((HttpStringResponse)_response).getBody();
                            double _result = Double.parseDouble(_responseBody);

                            //let the caller know of the success
                            callBack.onSuccess(_context, _result);
                        } catch (APIException error) {
                            //let the caller know of the error
                            callBack.onFailure(_context, error);
                        } catch (Exception exception) {
                            //let the caller know of the caught Exception
                            callBack.onFailure(_context, exception);
                        }
                    }
                    public void onFailure(HttpContext _context, Throwable _error) {
                        //invoke the callback after response if its not null
                        if (getHttpCallBack() != null)
                        {
                            getHttpCallBack().OnAfterResponse(_context);
                        }

                        //let the caller know of the failure
                        callBack.onFailure(_context, _error);
                    }
                });
            }
        };

        //execute async using thread pool
        APIHelper.getScheduler().execute(_responseTask);
    }

}