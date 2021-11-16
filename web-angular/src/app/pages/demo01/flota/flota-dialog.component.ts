import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FlotaFilter } from 'src/app/core/model/base-model.model';
import { EntityFilterService } from 'src/app/core/service/baseentity.service';
import { BaseNavRecord } from 'src/app/core/util/base-nav-record';

@Component({
  selector: 'app-flota-dialog',
  templateUrl: './flota-dialog.component.html',
  styleUrls: ['./flota-dialog.component.scss']
})
export class FlotaDialogComponent extends BaseNavRecord<FlotaFilter, number> implements OnInit {
  
  formFilter: FormGroup;

  filterCliente: string;

  filterFlota: string;
  
  filterNumguia: string;

  constructor(private serviceFilter: EntityFilterService, private formBuilder: FormBuilder, 
    public modal: NgbActiveModal) {
    super();
  }

  ngOnInit(): void {
    super.ngOnInit();

    this.formFilter = this.formBuilder.group({
      "filterCliente": ['', Validators.nullValidator],
      "filterFlota": ['', Validators.nullValidator],
      "filterNumguia": ['', Validators.nullValidator],
    });
  }

  doLoad(): void {
    this.rows = [];
    this.serviceFilter.getFlotas(0, this.filterCliente, this.filterFlota, this.filterNumguia).subscribe(
      res => {
        this.rows = res;
      }
    );
  }

}
