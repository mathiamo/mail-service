# mailApi

Hosted on https://gmailapi.herokuapp.com/

Can receive json with these parameters on https://gmailapi.herokuapp.com/sendMail:
  String subject;
  String body;
  String[] recievers;

valid json example:
  {
  "subject":
  "Dine interesser",
  "body":
  "many interesting things",
  "receivers":
  ["abc@mail.com","bca@mail.com"]
  }
