// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ClientProtocol.proto

package com.fictionalrealm.osserc.protocol.cp;

public final class ClientProtocol {
  private ClientProtocol() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_osserc_cp_InitUser_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_osserc_cp_InitUser_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024ClientProtocol.proto\022\tosserc.cp\032\017DataT" +
      "ypes.proto\"=\n\010InitUser\022\020\n\010username\030\001 \002(\t" +
      "\022\020\n\010password\030\002 \002(\t\022\r\n\005token\030\003 \001(\tB;\n%com" +
      ".fictionalrealm.osserc.protocol.cpB\016Clie" +
      "ntProtocolH\001P\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_osserc_cp_InitUser_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_osserc_cp_InitUser_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_osserc_cp_InitUser_descriptor,
              new java.lang.String[] { "Username", "Password", "Token", },
              com.fictionalrealm.osserc.protocol.cp.InitUser.class,
              com.fictionalrealm.osserc.protocol.cp.InitUser.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.fictionalrealm.osserc.protocol.datatypes.DataTypes.getDescriptor(),
        }, assigner);
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}
