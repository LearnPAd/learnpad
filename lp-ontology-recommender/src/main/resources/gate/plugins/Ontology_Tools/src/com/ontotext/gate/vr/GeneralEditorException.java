package com.ontotext.gate.vr;

import gate.util.GateException;

/** A general editor exception */
public class GeneralEditorException extends GateException {

  /** The base of the exception message */
  private static final String BASE = "General Editor Exception:\n";


  public GeneralEditorException(String msg) {
    super(BASE+msg);
  }
}