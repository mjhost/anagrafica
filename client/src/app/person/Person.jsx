import React from 'react';
// import {Link} from 'react-router';
import {Button} from 'react-bootstrap';

export default class Person extends React.Component{

	static propTypes = {
		match: React.PropTypes.object
	}

	render (){
		return <div>
			Una Persona conosciuta: {this.props.match.params.id} <br/>
			<Button href={`#/person/${this.props.match.params.id}/edit`}>Modifica</Button>
		</div>;
	}
}