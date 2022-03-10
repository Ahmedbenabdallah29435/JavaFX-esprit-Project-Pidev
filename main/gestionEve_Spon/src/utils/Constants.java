/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.scene.effect.BoxBlur;

public class Constants {

    public static final String INFORMATION_IMAGE = "/ressource/information.png";
    public static final String ERROR_IMAGE = "/ressource/error.png";
    public static final String SUCCESS_IMAGE = "/ressource/success.png";
    
    public static final String MESSAGE_ERROR_CONNECTION_MYSQL = "An error occurred when connecting to MySQL.\nCheck your connection to MySQL";
    
    public static final String MESSAGE_ADDED = "operation work with success";
    public static final String MESSAGE_Error = "oops something went wrong";
    public static final String MESSAGE_UPDATED = "Donnée été Modifier avec succès ";
    public static final String MESSAGE_DELETED = "Donnée été supprimer avec succès ";

    public static final String MESSAGE_IMAGE_LARGE = "Please upload a picture smaller than 1 MB.";
    public static final String MESSAGE_IMAGE_NOT_FOUND = "Image not found. The record will be saved.";
    public static final String MESSAGE_INSUFFICIENT_DATA = "Insufficient data";
    public static final String MESSAGE_ENTER_AT_LEAST_4_CHARACTERES = "Please enter at least 4 characters";

    public static final BoxBlur BOX_BLUR_EFFECT = new BoxBlur(3, 3, 3);
}
