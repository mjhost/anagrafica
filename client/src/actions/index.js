import * as dashboard from './dashboard';
import * as person from './person';
import * as visits from './visits';

module.exports = {
	...dashboard,
	...person,
	...visits
};