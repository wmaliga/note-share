import { Component, OnInit } from '@angular/core';
import { DatePipe } from "@angular/common";
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";

import { ToastrService } from "ngx-toastr";

import { NoteService } from "../../service/note.service";
import { NoteType } from "../../model/note.model";

@Component({
  selector: 'app-note-share',
  templateUrl: './note-share.component.html',
  styleUrls: ['./note-share.component.css']
})
export class NoteShareComponent implements OnInit {

  private readonly DATE_REGEX = '^(20)\\d\\d(-)(0[1-9]|1[012])\\2(0[1-9]|[12][0-9]|3[01])$';

  readonly noteType = NoteType;

  form: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly datePipe: DatePipe,
    private readonly noteService: NoteService,
    private readonly toast: ToastrService
  ) {
    this.form = this.newForm();
  }

  ngOnInit(): void {
  }

  shareNote() {
    this.noteService.shareNote(this.form.value)
      .subscribe({
        next: () => this.onShareSuccess(),
        error: error => this.onShareError(error)
      });
  }

  private onShareSuccess() {
    this.form.reset();
    this.toast.success("Note shared successfully!")
  }

  private onShareError(error: any) {
    this.toast.error("Ups! Something went wrong...")
    console.log(error);
  }

  clearForm() {
    this.form = this.newForm();
  }

  private newForm() {
    return this.formBuilder.group({
      type: [NoteType.PRIVATE, Validators.required],
      password: [''],
      title: ['', Validators.required],
      expirationDate: [this.dateAfter(10), [Validators.required, Validators.pattern(this.DATE_REGEX), NoteShareComponent.futureDateValidator]],
      data: ['', Validators.required]
    });
  }

  private dateAfter(days: number) {
    let date = new Date();
    date.setDate(date.getDate() + days);
    return this.datePipe.transform(date, 'YYYY-MM-dd');
  }

  private static futureDateValidator(control: FormControl) {
    return new Date(Date.parse(control.value)) > new Date() ? null : {
      'futureDateValidator': {
        valid: false
      }
    };
  }
}
