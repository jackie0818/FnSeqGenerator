/**
 * 
 */
package com.codeway.service;

import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author yixli
 *
 */
@Path("/out")
public class FnSeqOutputService implements IFnSeqService {
    private static String CLASS = FnSeqOutputService.class.getName();
    private static Logger logger = Logger.getLogger(CLASS);
	// pre cache Fn sequence with 200 items
    private static final int FN_CACHE_SIZE = 200;
	private static final String FN_SEQ_CACHE; //cached Fn sequence
    private static final int[] FN_INDEX = new int[FN_CACHE_SIZE]; // the cut-off index in cached Fn sequence for each item
    private static BigInteger LAST_BUT_ONE = null; // here is No. FN_CACHE_SIZE-1
    private static BigInteger LAST = null; //here is No. FN_CACHE_SIZE
	// build up the FN sequence cache
    static {
        FN_SEQ_CACHE = initFnCache();
	}
	
	  @Path("{sn}")
	  @GET
	  @Produces("application/json")
	  public Response fnOutput(@PathParam("sn") String sn) {
          String method = "fnOutput";
          logger.entering(CLASS, method);
          if (logger.isLoggable(Level.INFO)) {
              logger.log(Level.INFO, "input parameter is " + sn);
          }
		 int n = 0;
		 try {
			 n = Integer.parseInt(sn);
		 } catch (NumberFormatException e) {
			 //ignore
		 }		
	    JSONObject jsonObject = new JSONObject();
		if (n < 1) { //invalid parameter
			jsonObject.put("Error", "Please use an integer more than 0 as parameter!");
            logger.exiting(CLASS, method);
			return Response.status(200).entity(jsonObject.toString()).build();
		}
		jsonObject.put("Fibonacci",  fnSeqOutput(n));
        logger.exiting(CLASS, method);
		return Response.status(200).entity(jsonObject.toString()).build();
	  }

    // output the Fn sequence of first n items.
    public String fnSeqOutput(int n/*numbers of items in Fn sequence*/) {
        if (n < 1) return "";
        if (n <= FN_CACHE_SIZE) { // totally in cache.
            return FN_SEQ_CACHE.substring(0, FN_INDEX[n-1]);
        }
        //partial in cache
        StringBuilder fnStr = new StringBuilder(FN_SEQ_CACHE);
        BigInteger[] a = {LAST_BUT_ONE, LAST};
        for (int i = FN_CACHE_SIZE; i < n; i++) {
            BigInteger cur = a[0].add(a[1]);
            a[0] = a[1];
            a[1] = cur;
            fnStr.append(cur.toString()).append(' ');
        }
        return fnStr.toString();
    }
    // to initialize the Fn seq cache
    private static String initFnCache(/*BigInteger[] cache*/) {
        StringBuilder cacheStr = new StringBuilder();
        BigInteger[] a = {new BigInteger("0"), new BigInteger("1")};
        int firstFew = Math.min(2, FN_CACHE_SIZE);
        int index = 0;
        for (int i = 0; i < firstFew; i++) {
            String ai = a[i].toString();
            cacheStr.append(ai).append(' ');
            index += ai.length();
            FN_INDEX[i] = index;
            index++; //add one ' '
        }
        BigInteger cur;
        for (int i = 2; i < FN_CACHE_SIZE; i++) {
            cur = a[1].add(a[0]);
            a[0] = a[1];
            a[1] = cur;
            String ai = cur.toString();
            cacheStr.append(ai).append(' ');
            index += ai.length();
            FN_INDEX[i] = index;
            index++; //add one ' '
        }
        LAST = a[1];
        LAST_BUT_ONE = a[0];
        return cacheStr.toString();
    }

}
