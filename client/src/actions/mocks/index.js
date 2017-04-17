const today = new Date();
const thisMonth = ("00" + (today.getMonth()+1)).substr(-2);
const thisDay = ("00" + (today.getDate())).substr(-2);
const thisYear = today.getFullYear();

export const dashboard = {
	"birthdays": [
		{id:1, name: "Tizio", years:45},
		{id:2, name: "Caio", years:4},
		{id:3, name: "Sempronio", years:25}
	],
	"weddings": [
		{husband:{id:4, name:"marito 1"}, wife:{id:5, name:"moglie 1"}, years:15},
		{husband:{id:6, name:"marito 2"}, wife:{id:7, name:"moglie 2"}, years:50}
	],
	"deaths": [
		{id:10, name:"Granpa", surname:"Zappy", years:10, relatives:[
			{id:8, name:"Zippy", surname:"Zappy"}
		]}
	],
	"events": [
		{id:9, name:"Pellegrinaggio a fatima", date:thisYear + "-05-04", people:5}
	],
	"todo":[
		{id:2, name:"Sempronio"},
		{id:7, name:"moglie 2"}
	]
};

export const persona_10 = {
	id:10,
	name: "Granpa",
	surname: "Zappy",
	birth: "1910-03-05",
	death: [2007, thisMonth, thisDay].join("-"),
	sex:"M",
	relatives: [
		{id:8, name:"Zippy", surname:"Zappy", relation:{type:"child", hops:2}}
	]
};

export const persona_8 = {
	id:8,
	name: "Zippy",
	surname: "Zappy",
	birth: "1980-11-05",
	birthplace: "Roma",
	hobby: ["nuoto", "fotografia"],
	job: ["barista"],
	address: {
		street: "via da qualche parte",
		number: "15",
		cap: '00100',
		city: "roma",
		country: "italia"
	},
	sex:"F",
	relatives: [
		{id:10, name:"Granpa", surname:"Zappy", relation: {type:"parent", hops:2}}
	],
	contacts: [
		{type:"tel", data:"06-06060606"},
		{type:"cell", data:"338-1111111"},
		{type:"email", data:"example@example.com"}
	]
};