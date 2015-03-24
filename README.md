<h1>Mail api</h1>
<p>Hosted on https://cibermailservice.herokuapp.com</p>
<p>Can receive json with these parameters on https://cibermailservice.herokuapp.com/sendMail:</p>
<ul>
  <li>String subject;</li>
  <li>String body;</li>
  <li>String[] recievers;</li>
</ul>

<h2>valid json example:</h2>

<ul>
  <li><code> {
  "subject":
  "Dine interesser",
  "body":
  "many interesting things",
  "receivers":
  ["abc@mail.com","bca@mail.com"]
  }</code></li>
</ul>
