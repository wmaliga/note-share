import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";

import { EMPTY, map, Observable, switchMap, take } from "rxjs";
import { ToastrService } from "ngx-toastr";

import { Note, NoteType } from "../../model/note.model";
import { NoteService } from "../../service/note.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: 'app-note',
  templateUrl: './note-details.component.html',
  styleUrls: ['./note-details.component.css']
})
export class NoteDetailsComponent implements OnInit {

  readonly noteType = NoteType;

  form: FormGroup;

  loading: boolean = true;
  id$: Observable<number> = EMPTY;
  type: NoteType | null = null;
  note: Note | null = null;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly formBuilder: FormBuilder,
    private readonly toast: ToastrService,
    private readonly noteService: NoteService,
  ) {
    this.form = this.formBuilder.group({
      password: ['', Validators.required]
    })
  }

  ngOnInit(): void {
    this.loadParams();
    this.loadNoteType();
  }

  private loadParams() {
    this.id$ = this.route.params.pipe(map(params => params['id']));
  }

  private loadNoteType() {
    this.id$.pipe(
      take(1),
      switchMap(id => this.noteService.getNoteType(id))
    ).subscribe(type => this.setNoteType(type));
  }

  private setNoteType(type: NoteType) {
    this.type = type;

    if (type === NoteType.PUBLIC) {
      this.loadNote()
    }
  }

  loadNote() {
    this.id$.pipe(
      take(1),
      switchMap(id => this.noteService.getNote(id, this.form.value.password))
    ).subscribe({
      next: note => this.setNote(note),
      error: () => this.toast.error("Password not accepted.")
    });
  }

  setNote(note: Note) {
    this.note = note;
    this.loading = false;
  }
}
