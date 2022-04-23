# DigitalSignature

Pad Digital Signature component.

## Licence

[MIT LICENSE](https://github.com/cortezvini97/DigitalSignature/blob/master/LICENSE)

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
implementation 'com.github.cortezvini97:DigitalSignature:1.0.0'
````

```xml
<com.vcinsidedigital.digitalsignature.DigitalSignature
        android:id="@+id/digitalSignature"
        android:layout_width="match_parent"
        android:layout_height="200dp" />
````

### Java

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

### Kotlin

```kotlin
var digitalSignature:DigitalSignature? = null
```

```kotlin
digitalSignature = findViewById(R.id.digitalSignature)

        digitalSignature?.setOnToSignListener(object: DigitalSignature.OnToSignListener{
            override fun onStartSignature() {
                Toast.makeText(applicationContext, "Start", Toast.LENGTH_SHORT).show()
            }

            override fun onSigned() {
                btnSave?.isEnabled = true
                btnClear?.isEnabled = true
            }

            override fun onClear()
            {
                btnSave?.isEnabled = false
                btnClear?.isEnabled = false
            }

            override fun onSave(tmpFile: String?)
            {
                finish()
                val intent:Intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra("filePath", tmpFile)
                startActivity(intent)
            }
        })

        btnSave?.setOnClickListener {
            digitalSignature?.save(applicationContext)
        }

        btnClear?.setOnClickListener {
            digitalSignature?.clear()
        }

```
