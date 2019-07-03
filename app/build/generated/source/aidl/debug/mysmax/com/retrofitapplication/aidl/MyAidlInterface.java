/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/ChethanRGowda/Documents/android_work_space/RetrofitApplication/app/src/main/aidl/mysmax/com/retrofitapplication/aidl/MyAidlInterface.aidl
 */
package mysmax.com.retrofitapplication.aidl;
// Declare any non-default types here with import statements

public interface MyAidlInterface extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements mysmax.com.retrofitapplication.aidl.MyAidlInterface
{
private static final java.lang.String DESCRIPTOR = "mysmax.com.retrofitapplication.aidl.MyAidlInterface";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an mysmax.com.retrofitapplication.aidl.MyAidlInterface interface,
 * generating a proxy if needed.
 */
public static mysmax.com.retrofitapplication.aidl.MyAidlInterface asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof mysmax.com.retrofitapplication.aidl.MyAidlInterface))) {
return ((mysmax.com.retrofitapplication.aidl.MyAidlInterface)iin);
}
return new mysmax.com.retrofitapplication.aidl.MyAidlInterface.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_calcFact:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _result = this.calcFact(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements mysmax.com.retrofitapplication.aidl.MyAidlInterface
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public int calcFact(int a) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(a);
mRemote.transact(Stub.TRANSACTION_calcFact, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_calcFact = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public int calcFact(int a) throws android.os.RemoteException;
}
