import React from 'react';

export default class Person extends React.Component{

	static propTypes = {
		match: React.PropTypes.object
	}

	render (){
		if(this.props.match.params.id){
			return <div>Modifico una Persona conosciuta: {this.props.match.params.id}</div>;
		}else{
			return <div>Creo una nuova persona</div>;
		}
	}
}