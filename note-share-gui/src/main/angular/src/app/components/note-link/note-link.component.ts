import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";

import { EMPTY, map, Observable } from "rxjs";

@Component({
  selector: 'app-note-link',
  templateUrl: './note-link.component.html',
  styleUrls: ['./note-link.component.css']
})
export class NoteLinkComponent implements OnInit {

  id$: Observable<string> = EMPTY;
  origin : any | null;

  constructor(
    private readonly route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.id$ = this.route.params.pipe(map(params => params['id']));
    this.origin = location.origin;
  }
}
