<h1>Mail api</h1>
<p>Hosted on heroku on https://cibermailservice.herokuapp.com</p>
<p>Can receive json with these parameters on https://cibermailservice.herokuapp.com/sendMail:</p>
<ul>
  <li>String subject;</li>
  <li>String body;</li>
  <li>String[] recievers;</li>
</ul>

<h2>valid json example:</h2>

```json
  {
  	"subject": "Dine interesser",
    	"body": "many interesting things",
    	"toRecipients": ["asd@asd.asd, test@testerson.com"],
    	"ccRecipients": ["do_you@even.lift"],
    	"bccRecipients": ["secret@copy.com"],
  	"attachments": [
  		{
  			"filename": "nameOfFileHere",
  			"mimeType": "mimeTypeOfFileHere",
  			"encodedFile": "base64EncodedFileHere"
  		}
  	]
  }
```
