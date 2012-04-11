/* * Copyright (c) 2011. Bump Technologies Inc.
*/
package com.bump.api;

public class BumpAPIIntents {
    /* Broadcast when the api has successfully connected to the server.
     * You may now call enableBumping to enable bumping and matching.
     */
    public static final String CONNECTED = "com.bumptech.api.connected";

    /* Broadcast when the api gets disconnected from the server.
     * You must wait until you receive a CONNECTED callback to do anything
     * with the api.
     */
    public static final String DISCONNECTED = "com.bumptech.api.disconnected";

    /* Broadcast when the phone triggers a bump.
     * You should expect to get a MATCHED, NOTMATCHED or DISCONNECTED response.
     */
    public static final String BUMPED = "com.bumptech.api.bumped";

    /* Broadcast when the API fails to match someone.
     * Always, the response to a bump.
    */
    public static final String NOT_MATCHED = "com.bumptech.api.notMatched";

    /* Broadcast when the API has matched someone.
     * This **must** be responded to by calling confirm.
     * Extras:
     *   long proposedChannelID (the proposed channel ID of the other party)
    */
    public static final String MATCHED = "com.bumptech.api.matched";
    public static final String MATCHED_EXTRA_PROPOSED_CHANNEL_ID = "proposedChannelID";

    /* Broadcast when a channel has been confirmed by both parties.
     * Extras:
     *   long channelID
    */
    public static final String CHANNEL_CONFIRMED = "com.bumptech.api.channelConfirmed";
    public static final String CHANNEL_CONFIRMED_EXTRA_CHANNEL_ID = "channelID";

    /* Broadcast when data is received from the other user in a session
     * Extras:
     *   long channelID (the channel on which the data was received) 
     *   byte[] data
    */
    public static final String DATA_RECEIVED = "com.bumptech.api.dataReceived";
    public static final String DATA_RECEIVED_EXTRA_CHANNEL_ID = "channelID";    
    public static final String DATA_RECEIVED_EXTRA_DATA = "data";
    
}
