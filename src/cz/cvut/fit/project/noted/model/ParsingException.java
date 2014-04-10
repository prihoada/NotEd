/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.project.noted.model;

/**
 * Throws on any xml parsing error.
 * @author Adam Příhoda
 */
public class ParsingException extends Exception
{
    public ParsingException(String message)
    {
        super(message);
    }
    
}
