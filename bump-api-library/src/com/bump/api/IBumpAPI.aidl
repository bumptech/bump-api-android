/* * Copyright (c) 2011. Bump Technologies Inc.
*/
package com.bump.api;


interface IBumpAPI {
    /* Call to initially configure the API. After this, the API
     * can begin trying to establish a connection to the bump
     * servers 

     * parameters
     *  apiKey: your Bump API key.  Get one at http://bu.mp/apiagree 
                ensure that this key is moved to Production mode before shipping:
                http://bu.mp/apideveloper
     *  userID: an ID that makes sense for this user in the context of your application. 
                simpler apps often use the device's name or user's name. 
    */
    void configure(in String key, in String userID);
   
    /* Returns the other user's ID on a given channel, if it exists. */
    String userIDForChannelID(in long channelID);

    /* This will enable bump detection, which means you will
     * start getting MATCHED and NOT_MATCHED intents
     * (when the user actualy bumps) */
    void enableBumping();

    /* This will disable bump detection */
    void disableBumping();

    /* Confirm a match with another user.
     * Should be sent in response to a MATCHED Intent */
    void confirm(in long channelID, in boolean result);

    /* Send data during to a channel.
     * Should only be called after receiving a
     * CHANNEL_CONFIRMED Intent */
    void send(in long channelID, in byte[] data);

    /**** FOR DEBUG ONLY ****
     * Trigger bump (as if the phone had actually bumped) */
    void simulateBump();
}
