// This file contains material supporting the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

package server.communication;

import server.models.split.FreeSplit;
import server.models.split.Split;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A message class used by the Observable layer of the OCSF in order to conserve
 * information about the originator of a message.
 *
 * @author Dr. Robert Lagani&egrave;re
 * @version July 2001
 */
public class SplitOriginatorMessage implements Serializable
{
  /**
   * The connection that originated the message
   */
  private ConnectionToClient originator;

  /**
   * The message describing the action wanted.
   */
  private String message;

  /**
   * Contains arguments necessary for the operation
   */
  private HashMap<String,String> arguments;

  /**
   * Split object, usually returned to clients to update their split state
   */
  private HashMap<String, Split> splits;

// Constructor ***************************************************************

  public SplitOriginatorMessage(ConnectionToClient originator, String message, HashMap<String, String> arguments, HashMap<String, Split> splits) {
    this.originator = originator;
    this.message=message;
    this.arguments=arguments;
    this.splits = splits;
  }

// Accessor methods *********************************************************

  /**
   * Returns the originating connection.
   *
   * @return The connection from which the message originated.
   */
  public ConnectionToClient getOriginator()
  {
    return originator;
  }

  /**
   * Returns the message's contents.
   *
   * @return The content of the message.
   */
  public String getMessage()
  {
    return message;
  }

  /**
   * Retrieves specific argument if found else null
   * @param arg
   * @return
   */
  public String getArgument(String arg) {
    return arguments.get(arg);
  }

  public void setOriginator(ConnectionToClient originator){
    this.originator=originator;
  }

  public Split getSplit() {
    Map.Entry<String, Split> entry = splits.entrySet().iterator().next();
    return entry.getValue();
  }

  public HashMap<String,String> getArguments(){
    return this.arguments;
  }



  public HashMap<String, Split> getSplits() {
    return splits;
  }

  @Override
  public String toString() {
    return "SplitOriginatorMessage{" +
            "originator=" + originator +
            ", message=" + message +
            ", arguments=" + arguments +
            ", splits=" + splits +
            '}';
  }
}
