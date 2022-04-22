# digital_signature

Pad Digital Signature component.

## Licence

[MIT LICENSE](https://github.com/cortezvini97/digital_signature/blob/master/LICENSE)

## Usage

Add it in your root build.gradle at the end of repositories

```gradle
allprojects {
  repositories {
   ...
   maven { url 'https://jitpack.io' }
  }
 }
````

Add the dependency

```gradle
implementation 'com.github.cortezvini97:digital_signature:1.0.0'
````

```xml
<com.vcinsidedigital.digitalsignature.DigitalSignature
        android:id="@+id/digitalSignature"
        android:layout_width="0dp"
        android:layout_height="0dp" />
````

```java
    private DigitalSignature digitalSignature;
```

```java
    digitalSignature = findViewById(R.id.digitalSignature);
    btnSave = findViewById(R.id.btn_Save);
    btnClear = findViewById(R.id.btn_Clear);

    digitalSignature.setOnToSignListener(new DigitalSignature.OnToSignListener() {
            @Override
            public void onStartSignature() {
                //Start Event
            }

            @Override
            public void onSigned() {
                //On onSigned Event 
            }

            @Override
            public void onClear() {
                //OnClear Event
            }

            @Override
            public void onSave(String tmpFile)
            {
                //OnSave Event
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                digitalSignature.save(getApplicationContext());
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                digitalSignature.clear();
            }
        });

```
