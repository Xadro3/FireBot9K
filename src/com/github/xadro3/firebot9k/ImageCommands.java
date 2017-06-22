package com.github.xadro3.firebot9k;

import com.github.flickr.Flickr;

/**
 * Created by fabio on 20.06.2017.
 */
public class ImageCommands {

    static Configuration cfg = Configuration.getInstance();


    Flickr flickr = new Flickr(cfg.getFlickrAPIKey(),cfg.getFlickrAPISecret());



}
