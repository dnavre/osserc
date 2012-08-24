// Generated by the protocol buffer compiler.  DO NOT EDIT!

package com.fictionalrealm.osserc.protocol.datatypes;

public enum DisconnectionReason
    implements com.google.protobuf.ProtocolMessageEnum {
  SERVER_SHUTDOWN(0, 0),
  WRONG_CREDENTIALS(1, 1),
  TIMEOUT(2, 2),
  OTHER(3, 3),
  ;
  
  public static final int SERVER_SHUTDOWN_VALUE = 0;
  public static final int WRONG_CREDENTIALS_VALUE = 1;
  public static final int TIMEOUT_VALUE = 2;
  public static final int OTHER_VALUE = 3;
  
  
  public final int getNumber() { return value; }
  
  public static DisconnectionReason valueOf(int value) {
    switch (value) {
      case 0: return SERVER_SHUTDOWN;
      case 1: return WRONG_CREDENTIALS;
      case 2: return TIMEOUT;
      case 3: return OTHER;
      default: return null;
    }
  }
  
  public static com.google.protobuf.Internal.EnumLiteMap<DisconnectionReason>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static com.google.protobuf.Internal.EnumLiteMap<DisconnectionReason>
      internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<DisconnectionReason>() {
          public DisconnectionReason findValueByNumber(int number) {
            return DisconnectionReason.valueOf(number);
          }
        };
  
  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(index);
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return com.fictionalrealm.osserc.protocol.datatypes.DataTypes.getDescriptor().getEnumTypes().get(1);
  }
  
  private static final DisconnectionReason[] VALUES = {
    SERVER_SHUTDOWN, WRONG_CREDENTIALS, TIMEOUT, OTHER, 
  };
  
  public static DisconnectionReason valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    return VALUES[desc.getIndex()];
  }
  
  private final int index;
  private final int value;
  
  private DisconnectionReason(int index, int value) {
    this.index = index;
    this.value = value;
  }
  
  // @@protoc_insertion_point(enum_scope:osserc.dt.DisconnectionReason)
}

