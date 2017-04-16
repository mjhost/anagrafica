const log = false;

export const ADD_VISIT = 'ADD_VISIT';

export const addVisit = (person) => ({
	type: ADD_VISIT,
	person
});

export const addToVisits = (person) => dispatch => {
	log && console.log("before: adding person to visits");
	dispatch(addVisit(person));
};