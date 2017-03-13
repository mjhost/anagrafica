// NODES

// PERSON

CREATE (
  n:Person {
    first_name:'Frances',
    last_name:'Gonzales',
    birth_date:'19/8/1984',
    birth_place:'Sumberrejo',
    education_level:'interdum venenatis',
    address_street:'7119 Eagan Junction',
    address_city:'Kuiyong',
    address_country:'Indonesia',
    home_phone:'62-(383)111-2888'
  }
)

CREATE (
  n:Person {
    first_name:'Anne',
    last_name:'Ruiz',
    birth_date:'28/12/1968',
    birth_place:'Porto Ferreira',
    death_date:'28/5/2001',
    death_place:'Gubskaya',
    job_title:'Electrical Engineer',
    education_level:'et magnis dis parturient montes',
    hobbies:'modellismo',
    address_street:'7119 Eagan Junction',
    address_city:'Kuiyong',
    address_country:'Indonesia',
    home_phone:'62-(383)111-2888'
  }
)

/*

{"first_name":"Jerry","last_name":"James","birth_date":"5/7/2006","birth_place":"Bihać","death_date":"5/12/1971","death_place":"Mtimbira","job_title":"Developer IV","education_level":"proin interdum mauris non ligula","hobbies":"cucina","address_street":"80 Rigney Lane","address_city":"Soio","address_country":"Bosnia and Herzegovina","home_phone":"387-(918)125-4943","mobile_phone":"255-(118)472-0097","email":"jjames2@netvibes.com"}
{"first_name":"Gloria","last_name":"Lee","birth_date":"16/6/2012","birth_place":"Podgornaya","education_level":"aenean auctor gravida sem","address_street":"86945 Autumn Leaf Place","address_zip":"357815","address_city":"Liugou","address_country":"Russia","home_phone":"7-(836)602-8034","mobile_phone":"1-(915)422-3581","email":"glee3@nbcnews.com"}
{"first_name":"Kathleen","last_name":"Ward","birth_date":"26/8/1978","birth_place":"Jibu","death_date":"25/4/1990","death_place":"Gombang","job_title":"Senior Cost Accountant","education_level":"mauris laoreet ut rhoncus aliquet","hobbies":"giardinaggio","address_street":"259 Summer Ridge Parkway","address_city":"Dzhankoy","address_country":"China","home_phone":"86-(813)616-5029","mobile_phone":"62-(602)262-8944","email":"kward4@prweb.com"}
{"first_name":"Brian","last_name":"Gibson","birth_date":"20/9/1970","birth_place":"Itō","education_level":"consectetuer eget rutrum","address_street":"624 Sloan Junction","address_zip":"414-0055","address_city":"Kabac","address_country":"Japan","home_phone":"81-(613)222-8268","mobile_phone":"52-(253)894-1719","email":"bgibson5@forbes.com"}
{"first_name":"Stephen","last_name":"Olson","birth_date":"14/11/1996","birth_place":"Toubao","death_date":"29/11/2008","death_place":"Huangchi","job_title":"Research Assistant II","education_level":"duis consequat","hobbies":"fitness","address_street":"72708 Pleasure Lane","address_city":"Titao","address_country":"China","home_phone":"86-(865)996-1140"}
{"first_name":"Keith","last_name":"Day","birth_date":"18/12/1998","birth_place":"Sotteville-lès-Rouen","death_date":"11/12/2011","death_place":"Neiba","job_title":"Programmer Analyst III","education_level":"odio cras mi pede malesuada","hobbies":"modellismo","address_street":"603 5th Junction","address_zip":"76304 CEDEX","address_city":"Tomteboda","address_state":"Haute-Normandie","address_country":"France","mobile_phone":"1-(487)547-7184","email":"kday7@nymag.com"}
{"first_name":"Henry","last_name":"Rice","birth_date":"6/11/1991","birth_place":"Phùng","death_date":"21/2/2000","death_place":"Non Sung","job_title":"Operator","education_level":"libero nullam sit","hobbies":"cinema","address_street":"73 American Ash Terrace","address_city":"Maringá","address_country":"Vietnam","home_phone":"84-(834)643-6697","mobile_phone":"66-(509)656-2507","email":"hrice8@sogou.com"}
{"first_name":"Jack","last_name":"Howell","birth_date":"30/6/1972","birth_place":"North York","death_date":"7/12/2009","death_place":"Georgīevka","job_title":"Social Worker","education_level":"ut suscipit a feugiat","hobbies":"modellismo","address_street":"058 Ruskin Alley","address_zip":"M3H","address_city":"Pingya","address_state":"Ontario","address_country":"Canada","home_phone":"1-(634)999-3049","mobile_phone":"7-(731)707-0690","email":"jhowell9@macromedia.com"}

*/

// EVENT

CREATE (
  n:Event {
    name:"matrimonio",
    description:"donec diam",
    date:"15/11/1951",
    place:"Dvorovi"
  }
)
/*

{"name":"battesimo","description":"donec diam","date":"15/11/1951","place":"Dvorovi"}
{"name":"funerale","description":"massa quis augue luctus tincidunt","date":"06/07/1961","place":"Vostok"}
{"name":"funerale","description":"justo etiam pretium iaculis justo","date":"30/07/1950","place":"Guyang"}
{"name":"cresima","description":"ut blandit non","date":"22/09/1969","place":"Shuyuan Zhen"}
{"name":"matrimonio","description":"fringilla rhoncus","date":"04/09/1997","place":"Martingança"}
*/




// EDGES

MATCH
  (a:Person),(b:Person)
WHERE
  a.first_name = 'Frances' AND a.last_name = 'Gonzales' AND
  b.first_name = 'Anne' AND b.last_name = 'Ruiz'
CREATE
  (a)-[r:IS_MARRIED_TO]->(b)

MATCH
  (a:Person),(m:Event)
WHERE
  a.first_name = 'Frances' AND a.last_name = 'Gonzales' AND
  m.date = "15/11/1951" and m.place = "Dvorovi"
CREATE
  (a)-[r:MARRIED]->(m)

MATCH
  (b:Person),(m:Event)
WHERE
  b.first_name = 'Anne' AND b.last_name = 'Ruiz' AND
  m.date = "15/11/1951" and m.place = "Dvorovi"
CREATE
  (b)-[r:MARRIED]->(m)
