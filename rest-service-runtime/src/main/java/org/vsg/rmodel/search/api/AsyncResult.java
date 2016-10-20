package org.vsg.rmodel.search.api;

public interface AsyncResult<T> {

	  /**
	   * The result of the operation. This will be null if the operation failed.
	   *
	   * @return the result or null if the operation failed.
	   */
	  T result();

	  /**
	   * A Throwable describing failure. This will be null if the operation succeeded.
	   *
	   * @return the cause or null if the operation succeeded.
	   */
	  Throwable cause();

	  /**
	   * Did it succeed?
	   *
	   * @return true if it succeded or false otherwise
	   */
	  boolean succeeded();

	  /**
	   * Did it fail?
	   *
	   * @return true if it failed or false otherwise
	   */
	  boolean failed();	
	
}
