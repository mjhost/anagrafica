import React from 'react';
import PropTypes  from 'prop-types';
import {connect} from 'react-redux';

class Visits extends React.Component {
	render (){
		return (
			<div>
				<div className="page-header">
					<h1>Visite programmate</h1>
				</div>
			</div>
		);
	}
}

Visits.propTypes = {
	dispatch: PropTypes.func.isRequired
};

export default connect()(Visits);