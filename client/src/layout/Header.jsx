import React from 'react';
import {Navbar} from 'react-bootstrap';
import {Link} from 'react-router-dom';


export default class Header extends React.Component {
	render() {
		return (
			<Navbar>
				<Navbar.Header>
					<Navbar.Brand>
						<Link to="/">Registro Parrocchiani</Link>
					</Navbar.Brand>
				</Navbar.Header>
			</Navbar>
		);
	}
}